package com.moon.libcommon.dialog;
// _ooOoo_  
// o8888888o  
// 88" . "88  
// (| -_- |)  
//  O\ = /O  
// ___/`---'\____  
// .   ' \\| |// `.  
// / \\||| : |||// \  
// / _||||| -:- |||||- \  
// | | \\\ - /// | |  
// | \_| ''\---/'' | |  
// \ .-\__ `-` ___/-. /  
// ___`. .' /--.--\ `. . __  
// ."" '< `.___\_<|>_/___.' >'"".  
// | | : `- \`.;`\ _ /`;.`/ - ` : | |  
// \ \ `-. \_ __\ /__ _/ .-` / /  
// ======`-.____`-.___\_____/___.-`____.-'======  
// `=---='  
//          .............................................  
//           佛曰：bug泛滥，我已瘫痪！

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.moon.libcommon.R;

/**
 * TeachAssistant
 * create by NiMa.Wang on 2020/2/25
 */

public class MoonInputConfirmPopupView extends MoonConfirmPopupView implements View.OnClickListener {

    public MoonInputConfirmPopupView(@NonNull Context context) {
        super(context);
    }

    /**
     * 绑定已有布局
     *
     * @param layoutId 在Confirm弹窗基础上需要增加一个id为et_input的EditText
     * @return
     */
    public MoonInputConfirmPopupView bindLayout(int layoutId) {
        bindLayoutId = layoutId;
        return this;
    }

    EditText et_input;
    public String inputContent;

    @Override
    protected void initPopupContent() {
        super.initPopupContent();
        et_input = findViewById(R.id.et_input);
        et_input.setVisibility(VISIBLE);
        if (TextUtils.isEmpty(content)) {
            tv_content.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(hint)) {
            et_input.setHint(hint);
        }
        if (!TextUtils.isEmpty(inputContent)) {
            et_input.setText(inputContent);
            et_input.setSelection(inputContent.length());
        }
    }

    public EditText getEditText() {
        return et_input;
    }


    OnCancelListener cancelListener;
    OnInputConfirmListener inputConfirmListener;

    public void setListener(OnInputConfirmListener inputConfirmListener, OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        this.inputConfirmListener = inputConfirmListener;
    }

    @Override
    public void onClick(View v) {
        if (v == tv_cancel) {
            if (cancelListener != null) cancelListener.onCancel();
            dismiss();
        } else if (v == tv_confirm) {
            if (inputConfirmListener != null)
                inputConfirmListener.onConfirm(et_input.getText().toString().trim());
            if (popupInfo.autoDismiss) dismiss();
        }
    }
}
