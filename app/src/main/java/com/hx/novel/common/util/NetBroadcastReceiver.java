package com.hx.novel.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hx.novel.R;
import com.hx.novel.common.constant.Constant;


public class NetBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        boolean networkConnected = NetworkHelper.isNetworkConnected(context);
        if (!networkConnected) {
            ToastHelper.showToast(context, context.getString(R.string.check_net_info_string));
            Constant.isNetworkConnected = false;
        } else {
            boolean wifiConnected = NetworkHelper.isWifiConnected(context);//wifi已连接
            boolean mobileConnected = NetworkHelper.isMobileConnected(context);//当前处于移动数据连接
            if (wifiConnected || mobileConnected) {
                Constant.isNetworkConnected = true;
            }
        }
    }

}
