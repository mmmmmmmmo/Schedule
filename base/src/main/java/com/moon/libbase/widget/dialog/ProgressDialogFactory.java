package com.moon.libbase.widget.dialog;

import android.app.Activity;
import android.text.TextUtils;



public final class ProgressDialogFactory {

    private ProgressDialogFactory() {

    }

    public static final class Builder {

        private Activity mActivity;
        private boolean mFinish;
        private String mTitle;
        private String mMessage;

        public Builder(Activity activity) {
            mActivity = activity;
        }

        public Builder canFinish(boolean finish) {
            mFinish = finish;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public ProgressDialog build() {
            ProgressDialog progressDialog = newInstance(mActivity, mFinish, mTitle, mMessage);
            return progressDialog;
        }
    }

    @Deprecated
    public static ProgressDialog newInstance(Activity activity, String message) {
        ProgressDialog progressDialog = newInstance(activity, false, null, message);
        return progressDialog;
    }

    @Deprecated
    public static ProgressDialog newInstance(Activity activity, boolean finish, String message) {
        ProgressDialog progressDialog = newInstance(activity, finish, null, message);
        return progressDialog;
    }

    @Deprecated
    public static ProgressDialog newInstance(Activity activity, boolean finish, String title, String message) {
        ProgressDialog progressDialog = new MaterialProgressDialog(activity, finish);
        if (!TextUtils.isEmpty(title)) {
            progressDialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            progressDialog.setMessage(message);
        }
        return progressDialog;
    }
}
