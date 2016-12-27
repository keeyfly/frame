package com.hx.novel.common.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Heart on 2016/8/22 0022.
 */
public class ToastHelper {


    private static Snackbar snackbar;
    private static Toast toast;
    private static ProgressBar progressBar;

    public static void showDialog(View view, String text) {
        if (snackbar == null) {
            snackbar = Snackbar.make(view,
                    text,
                    Snackbar.LENGTH_SHORT);
        } else {
            snackbar.setText(text);
        }
        snackbar.show();

    }

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showProgress(Context context) {

        if (progressBar == null) {

            progressBar = new ProgressBar(context);
            // progressBar.setProgressTintMode();
        }

    }

}
