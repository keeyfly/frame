package com.hx.novel.common.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;

import com.hx.novel.R;


/**
 * Created by Administrator on 2016/10/8.
 */
public class MenuHelper {

    public static void setMenuBackground(Context mContext) {
        // 关键代码为重写Layout.Factory.onCreateView()方法自定义布局
        ((Activity) mContext).getLayoutInflater().setFactory(new LayoutInflater.Factory() {
            /**
             * name - Tag name to be inflated.<br/>
             * context - The context the view is being created in.<br/>
             * attrs - Inflation attributes as specified in XML file.<br/>
             */
            public View onCreateView(String name, final Context context, AttributeSet attrs) {
                // 指定自定义inflate的对象
                if (name.equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")) {
                    try {
                        LayoutInflater f = ((Activity) context)
                                .getLayoutInflater();
                        final View view = f.createView(name, null, attrs);// 尝试创建我们自己布局
                        new Handler().post(new Runnable() {
                            public void run() {
                                view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                                // view.setBackgroundResource(R.drawable.menu_bg);// 设置背景为我们自定义的图片，替换cwj_bg文件即可
                            }
                        });
                        return view;
                    } catch (InflateException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        });
    }
}
