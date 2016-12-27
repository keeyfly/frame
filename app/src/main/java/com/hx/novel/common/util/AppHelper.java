package com.hx.novel.common.util;

import android.util.DisplayMetrics;

/**
 * Created by 李贺翔 on 2016/12/20.
 * Description:获取屏幕信息
 */
public class AppHelper {
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    private static final class AppHelperHoler {
        public static AppHelper appHelper = new AppHelper();
    }

    public static AppHelper getInstence() {
        return AppHelperHoler.appHelper;
    }

    public int height() {
        return (int) (displayMetrics.heightPixels * displayMetrics.density);
    }

    public int width() {
        return (int) (displayMetrics.widthPixels * displayMetrics.density);
    }
}
