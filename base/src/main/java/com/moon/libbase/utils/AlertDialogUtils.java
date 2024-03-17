package com.moon.libbase.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.WindowManager;

import com.moon.libbase.R;

/**
 * 常用的dialog封装
 */
public class AlertDialogUtils {

    public static AlertDialog dialog;

    /**
     * 显示带标题和内容的双按钮dialog
     */
    public static void showNormalAlert(Context context, CharSequence title, CharSequence message, OnClickListener posListener, OnClickListener canListener, boolean cancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, posListener)
                .setNegativeButton(R.string.cancel, canListener)
                .setCancelable(cancelable)
                .show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                AlertDialogUtils.dialog = null;
            }
        });
    }

    public static void showSystemNormalAlert(Context context, CharSequence title, CharSequence message, OnClickListener posListener, OnClickListener canListener, boolean cancelable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, posListener)
                .setNegativeButton(R.string.cancel, canListener)
                .setCancelable(cancelable)
                .show();
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                AlertDialogUtils.dialog = null;
            }
        });
    }

    public static void showSystemNormalAlert(Context context, CharSequence title, CharSequence message, OnClickListener posListener) {
        showNormalAlert(context, title, message, posListener, null, true);
    }

    /**
     * 显示带标题和内容的双按钮dialog
     */
    public static void showNormalAlert(Context context, CharSequence title, CharSequence message, OnClickListener posListener, OnClickListener canListener) {
        showNormalAlert(context, title, message, posListener, canListener, true);
    }

    public static void showNormalAlert(Context context, CharSequence title, int messageId, OnClickListener posListener, OnClickListener canListener) {
        showNormalAlert(context, title, context.getString(messageId), posListener, canListener);
    }

    public static void showNormalAlert(Context context, int title, int messageId, OnClickListener posListener, OnClickListener canListener) {
        showNormalAlert(context, context.getString(title), context.getString(messageId), posListener, canListener);
    }

    public static AlertDialog showOperationAlert(Context context, int messageId, int posTextId, int negTextId, int neuTextId, OnClickListener clickListener) {
        return new AlertDialog.Builder(context)
                .setMessage(messageId)
                .setPositiveButton(posTextId, clickListener)
                .setNegativeButton(negTextId, clickListener)
                .setNeutralButton(neuTextId, clickListener)
                .create();
    }

    public static AlertDialog showMessageDialog(Context context, int messageId, int posId, OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setMessage(messageId)
                .setPositiveButton(posId, listener)
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    public static AlertDialog showMessageDialog(Context context, int messageId, OnClickListener listener) {
        return showMessageDialog(context, messageId, R.string.ok, listener);
    }

    public static AlertDialog showListItem(Context context,OnClickListener listener,String... items){
        return new AlertDialog.Builder(context)
                .setItems(items, listener)
                .show();
    }
}
