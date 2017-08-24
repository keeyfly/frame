package com.hx.novel.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hx.novel.MainActivity;
import com.hx.novel.R;
import com.hx.novel.frame.base.BaseSliderActivity;


public class LoginActivity extends BaseSliderActivity implements LoginView {

    private Button btn;
    LoginPresenterImpl loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter.attachView(this);
        btn = ((Button) findViewById(R.id.btn_start_intent));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.login("", "");
            }
        });
    }

    @Override
    public void requestLoginSuccessListener(LoginInfo response) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void requestFiledListener(String result) {

    }

    @Override
    protected void onDestroy() {
        if (null != loginPresenter)
            loginPresenter.detachView();
        super.onDestroy();
    }
}
