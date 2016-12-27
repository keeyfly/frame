package com.mylibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by ac-er on 2015/11/3.
 */
public class SCListView extends ListView {
    public SCListView(Context context) {
        super(context);
    }

    public SCListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SCListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 5, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
