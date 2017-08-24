package com.hx.novel.login;


import com.hx.novel.frame.bean.FrameJson;
import com.hx.novel.frame.http.FrameRetrofit;
import com.hx.novel.frame.model.FrameModel;
import com.hx.novel.frame.view.FrameRequestCallBack;

import rx.Observable;
import rx.Subscription;


public class LoginModel extends FrameModel {

   /* public LoginModel() {
        // super(context, frameRequestFailListener);
    }*/

    public Subscription getLogin(FrameRequestCallBack frameRequestCallBack) {
        Observable<FrameJson> loginResult = FrameRetrofit.Builder(mContext).getApiService().getLoginResult();
        return responseEntity(loginResult, frameRequestCallBack);
    }
}
