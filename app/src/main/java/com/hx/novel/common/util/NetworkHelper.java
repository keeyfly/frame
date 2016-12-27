package com.hx.novel.common.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

/**
 * Created by 李贺翔 on 2016/8/30.
 */
public class NetworkHelper {

    private static final String TAG = "NetworkHelper";


    /**
     * 判断是否启动定位服务
     *
     * @param context 全局信息接口
     * @return 是否启动定位服务
     */
    public static boolean isOpenLocService(final Context context) {

        boolean isGps = false; //判断GPS定位是否启动
        boolean isNetwork = false; //判断网络定位是否启动

        if (context != null) {

            LocationManager locationManager
                    = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            if (locationManager != null) {
                //通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
                isGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                //通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
                isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            }

            if (isGps || isNetwork) {
                return true;
            }

        }

        return false;
    }

    /**
     * 判断是否启动全部网络连接，包括WIFI和流量
     *
     * @param context 全局信息接口
     * @return 是否连接到网络
     */
    public static boolean isNetworkConnected(Context context) {

        if (context != null) {

            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }

        }
        return false;
    }

    /**
     * 判断是否启动WIFI连接
     *
     * @param context 全局信息接口
     * @return 是否连接到WIFI
     */
    public static boolean isWifiConnected(Context context) {

        if (context != null) {

            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            if (wifi != null) {
                return wifi.isWifiEnabled();
            }

        }

        return false;
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */

    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取当前网络连接的类型信息
     *
     * @param context
     * @return
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
}
