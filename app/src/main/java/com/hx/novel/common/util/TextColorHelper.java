package com.hx.novel.common.util;

import android.support.design.widget.TextInputLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * 修改TextInputLayout错误提示语颜色
 */
public class TextColorHelper {

    public static void setErrorTextColor(TextInputLayout textInputLayout, int color) {
        try {
            Field fErrorView = TextInputLayout.class.getDeclaredField("mErrorView");
            fErrorView.setAccessible(true);
            TextView mErrorView = (TextView) fErrorView.get(textInputLayout);
            Field fCurTextColor = TextView.class.getDeclaredField("mCurTextColor");
            fCurTextColor.setAccessible(true);
            fCurTextColor.set(mErrorView, color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
