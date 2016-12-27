package com.hx.novel.common.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hx.novel.R;


/**
 * Created by 李贺翔 on 2016/12/7.
 * Description:页面等待框
 */
public class DialogHelper {

    private static Dialog dialog;

    private static void setContentView(Context context) {
        dialog = new Dialog(context, R.style.dialog_setting);
        View inflate = LayoutInflater.from(context).inflate(R.layout.wait_dialog, null, false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        initParams();
    }

    private static void initParams() {
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);
        initView();
    }

    private static void initView() {
        ImageView imageView = ((ImageView) dialog.findViewById(R.id.dialog_image));
        imageView.setImageResource(R.drawable.view_anim_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }

    public static void dismiss() {
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialog = null;
        }
    }

    public static void show(Context context) {
        try {
            if (dialog != null) {
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            } else {
                setContentView(context);
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
