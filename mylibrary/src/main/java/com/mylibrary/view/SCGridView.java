package com.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by chuan on 11/4/2015.
 */
public class SCGridView extends GridView {
    public SCGridView(Context context) {
        super(context);
    }

    public SCGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SCGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >>5, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
