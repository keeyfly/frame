package com.hx.novel.common.util;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * Created by Administrator on 2016/9/21.
 */
public class ProgressHelper {

    private static ProgressDialog dialog;

    public static void show(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("正在登录");
        dialog.show();
    }

    public static void dismiss() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public static void cancel() {
        if (dialog != null) {
            dialog.cancel();
        }
    }
}
