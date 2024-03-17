package com.moon.libbase.widget.dialog;


public interface ProgressDialog {

    void setTitle(String title);

    void setMessage(String message);

    boolean isShowing();

    void show();

    void dismiss();

    void setCancelable(boolean cancel);
}
