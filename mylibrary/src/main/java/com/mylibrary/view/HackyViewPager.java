package com.mylibrary.view;

import android.support.v4.view.ViewPager;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/12/5.
 * 解决与photoView使用的缩小放大的bug
 */

public class HackyViewPager extends ViewPager {

    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {

            return false;
        }
    }
}

