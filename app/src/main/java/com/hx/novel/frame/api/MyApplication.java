package com.hx.novel.frame.api;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.anupcowkur.reservoir.Reservoir;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hx.novel.common.util.FrescoHelper;
import com.hx.novel.common.util.NetBroadcastReceiver;

/**
 * Created by 李贺翔 on 2016/8/30.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private NetBroadcastReceiver receiver;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
        Fresco.initialize(this, FrescoHelper.getImagePipelineConfig(this));
        try {
            Reservoir.init(this, 2048);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CheckNet();
    }

    private void CheckNet() {
        receiver = new NetBroadcastReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, mFilter);
    }

    public void unRegisterReceiver() {
        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

}
