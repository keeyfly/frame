package com.hx.novel.frame.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.hx.novel.R;
import com.hx.novel.common.util.DialogHelper;
import com.hx.novel.frame.api.MyApplication;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * Created by Administrator on 2016/10/20.
 */
public class BaseCompatActivity extends RxAppCompatActivity {
    public Context mContext;
    private MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (application == null)
            application = new MyApplication();
        application.unRegisterReceiver();
        application = null;
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void finish() {
        super.finish();
        DialogHelper.dismiss();
    }
}
