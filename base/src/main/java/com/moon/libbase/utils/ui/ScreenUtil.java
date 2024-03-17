package com.moon.libbase.utils.ui;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;


public class ScreenUtil {

    public static DisplayMetrics getScreenMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int getScreenWidth(Context context) {
        return getScreenMetrics(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return getScreenMetrics(context).heightPixels;
    }

    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getApplicationContext().getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    public static void setStatusBarHeightPadding(Context context, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int height = getStatusBarHeight(context);
            if (height > 0) {
                view.setPadding(view.getPaddingLeft(), height, view.getPaddingRight(), view.getPaddingBottom());
            }
        }
    }

}
