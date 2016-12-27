package com.hx.novel.login;


import com.hx.novel.frame.view.FrameView;

public interface LoginView extends FrameView {
    void requestLoginSuccess(LoginInfo loginInfo);
}
