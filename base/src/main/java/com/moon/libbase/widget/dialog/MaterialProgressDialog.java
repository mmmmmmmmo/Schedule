package com.moon.libbase.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.moon.libbase.R;


public class MaterialProgressDialog extends Dialog implements ProgressDialog {

    private Activity mActivity;
    private String mTitle;
    private String mMessage;
    private boolean mFinish;
    private TextView title;
    private TextView message;

    public MaterialProgressDialog(Activity activity, boolean finish) {
        super(activity, com.lxj.xpopup.R.style.Theme_AppCompat_Light_Dialog);
        mActivity = activity;
        mFinish = finish;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);

        setTitle(mTitle);
        setMessage(mMessage);

        setCancelable(true);
        setCanceledOnTouchOutside(false);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFinish) {
            mActivity.finish();
        }
    }

    @Override
    public void setTitle(String titleStr) {
        mTitle = titleStr;
        if (!TextUtils.isEmpty(mTitle)) {
            title.setText(mTitle);
            title.setVisibility(View.VISIBLE);
        } else {
            title.setVisibility(View.GONE);
        }
    }

    @Override
    public void setMessage(String messageStr) {
        mMessage = messageStr;
        if (!TextUtils.isEmpty(mMessage)) {
            message.setText(mMessage);
            message.setVisibility(View.VISIBLE);
        } else {
            message.setVisibility(View.GONE);
        }
    }
}
