package com.hx.novel.login;


import com.hx.novel.frame.model.IModel;
import com.hx.novel.frame.presenter.BasePresenter;
import com.hx.novel.frame.view.FrameRequestCallBack;

import java.util.HashMap;

public class LoginPresenterImpl extends BasePresenter<LoginView> {

    public void login(String name, String pwd) {
        if (isViewAttached()) {
            ((LoginModel) getModelMap().get("login")).getLogin(new FrameRequestCallBack() {
                @Override
                public void requestSuccessListener(Object response) {
                    getView().requestLoginSuccessListener((LoginInfo) response);
                }
            });
        }
    }

    @Override
    public HashMap<String, IModel> getModelMap() {
        return loadModelMap(new LoginModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String, IModel> map = new HashMap<>();
        map.put("login", models[0]);
        return map;
    }
}
