package com.hx.novel.frame.model;

import android.content.Context;

import com.hx.novel.R;
import com.hx.novel.common.constant.Constant;
import com.hx.novel.frame.api.RequestException;
import com.hx.novel.frame.bean.FrameJson;
import com.hx.novel.frame.view.FrameRequestCallBack;
import com.hx.novel.frame.view.FrameRequestFailListener;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FrameModel<T> {
    protected FrameRequestFailListener frameRequestFailListener;
    protected Observable.Transformer transformer;
    protected Context mContext;

    public FrameModel(Context mContext, FrameRequestFailListener frameRequestFailListener) {
        this.mContext = mContext;
        this.frameRequestFailListener = frameRequestFailListener;
    }

    public void setTransformer(Observable.Transformer transformer) {
        this.transformer = transformer;
    }

    protected Subscription responseEntity(Observable<FrameJson<R>> observable, final FrameRequestCallBack<T> frameRequestCallBack) {

        Subscriber<T> subscriber = new Subscriber<T>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                try {
                    frameRequestFailListener.requestFiledListener(e.getMessage());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onNext(T rResponse) {
                try {
                    frameRequestCallBack.requestSuccessListener(rResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            if (Constant.isNetworkConnected) {
                Observable<T> observable1 = observable.map(new HttpResultFunc<R>()).subscribeOn(Schedulers.io());
                if (transformer != null)
                    observable1.compose(transformer);
                observable1.observeOn(AndroidSchedulers.mainThread());
                Subscription subscribe;
                subscribe = observable1.subscribe(subscriber);
                return subscribe;
            } else {
                frameRequestFailListener.requestFiledListener("网络未连接，请先连接网络");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class HttpResultFunc<R> implements Func1<FrameJson<R>, T> {

        @Override
        public T call(FrameJson<R> httpResult) {
            if (!httpResult.status && httpResult.code != 1) {
                throw new RequestException(httpResult.code);
            }
            if (httpResult.data == null) {
                return (T) httpResult;
            } else {
                return (T) httpResult.data;
            }
        }
    }
}
