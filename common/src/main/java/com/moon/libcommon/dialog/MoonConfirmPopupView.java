package com.moon.libcommon.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.util.XPopupUtils;
import com.moon.libcommon.R;

/**
 * @author ry
 * @date 2019-08-17
 */
public class MoonConfirmPopupView extends CenterPopupView implements View.OnClickListener {
    OnCancelListener cancelListener;
    OnConfirmListener confirmListener;
    public TextView tv_title, tv_content, tv_cancel, tv_confirm;
    String title, content, hint, cancelText, confirmText;
    boolean isHideCancel = false;
    int cancelTextColor, confirmTextColor;

    public MoonConfirmPopupView(@NonNull Context context) {
        super(context);
    }

    /**
     * 绑定已有布局
     *
     * @param layoutId 要求布局中必须包含的TextView以及id有：tv_title，tv_content，tv_cancel，tv_confirm
     */
    public MoonConfirmPopupView bindLayout(int layoutId) {
        bindLayoutId = layoutId;
        return this;
    }

    @Override
    protected int getImplLayoutId() {
        return bindLayoutId != 0 ? bindLayoutId : R.layout.xpopup_center_impl_moon;
    }

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_confirm = findViewById(R.id.tv_confirm);

        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);

        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        } else {
            tv_title.setVisibility(GONE);
        }

        if (!TextUtils.isEmpty(content)) {
            tv_content.setText(content);
        }
        if (!TextUtils.isEmpty(cancelText)) {
            tv_cancel.setText(cancelText);
        }
        if (!TextUtils.isEmpty(confirmText)) {
            tv_confirm.setText(confirmText);
        }
        if (isHideCancel) {
            tv_cancel.setVisibility(View.GONE);
        }
        if (confirmTextColor != 0) tv_confirm.setTextColor(confirmTextColor);

        if (cancelTextColor != 0) tv_cancel.setTextColor(cancelTextColor);
    }

    public MoonConfirmPopupView setListener(OnConfirmListener confirmListener, OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        this.confirmListener = confirmListener;
        return this;
    }

    public MoonConfirmPopupView setTitleContent(String title, String content, String hint) {
        this.title = title;
        this.content = content;
        this.hint = hint;
        return this;
    }

    public MoonConfirmPopupView setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public MoonConfirmPopupView setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    public MoonConfirmPopupView hideCancelBtn() {
        isHideCancel = true;
        return this;
    }

    public MoonConfirmPopupView setConfirmTextColor(int confirmTextColor) {
        this.confirmTextColor = confirmTextColor;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            dismiss();
            if (cancelListener != null) cancelListener.onCancel();
        } else if (v == tv_confirm) {
            if (confirmListener != null) confirmListener.onConfirm();
            if (popupInfo.autoDismiss) dismiss();
        }
    }

    @Override
    protected int getMaxWidth() {
        return popupInfo.maxWidth == 0 ? (int) (XPopupUtils.getWindowWidth(getContext()) * 0.8f)
                : popupInfo.maxWidth;
    }
}
