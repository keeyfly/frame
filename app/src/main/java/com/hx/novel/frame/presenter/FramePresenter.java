package com.hx.novel.frame.presenter;

import com.hx.novel.frame.view.FrameRequestFailListener;
import com.hx.novel.frame.view.FrameView;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @param <V> 视图接口对象(view) 具体业务各自继承自FrameView
 * @param <>  业务请求返回的具体对象
 */
public class FramePresenter<V extends FrameView> implements FrameRequestFailListener {

    protected FrameView frameView;
    protected CompositeSubscription mCompositeSubscription;
    protected Observable.Transformer transformer;

    public void onUnSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscriber) {
        if (subscriber != null) {
            if (mCompositeSubscription == null) {
                mCompositeSubscription = new CompositeSubscription();
            }
            mCompositeSubscription.add(subscriber);
        }
    }

    public void setTransformer(Observable.Transformer transformer) {
        this.transformer = transformer;
    }

    public FramePresenter(V frameView) {
        this.frameView = frameView;
    }

    @Override
    public void requestFiledListener(String result) {
        frameView.requestFiledListener(result);
    }
}
