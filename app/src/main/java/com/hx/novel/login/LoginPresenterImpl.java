package com.hx.novel.login;


import android.content.Context;

import com.hx.novel.frame.presenter.FramePresenter;
import com.hx.novel.frame.view.FrameRequestCallBack;
import com.hx.novel.frame.view.FrameView;

public class LoginPresenterImpl extends FramePresenter {

    protected LoginModel loginModel;

    public LoginPresenterImpl(Context mContext) {
        super((FrameView) mContext);
        loginModel = new LoginModel(mContext, this);
    }

    public void getLogin() {
        addSubscription(loginModel.getLogin(new FrameRequestCallBack<LoginInfo>() {
            @Override
            public void requestSuccessListener(LoginInfo loginInfo) {
                ((LoginView) frameView).requestLoginSuccess(loginInfo);
            }
        }));
    }

}
