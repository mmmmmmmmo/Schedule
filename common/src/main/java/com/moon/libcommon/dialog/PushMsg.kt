package com.moon.libcommon.dialog

import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class PushMsg constructor(context: Context) : CenterPopupView(context) {
    var msg: String = ""
    var tv_content: TextView? = null
    var ok: Button? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_push_msg
    }

    override fun onCreate() {
        super.onCreate()
        tv_content = findViewById(R.id.tv_content)
        ok = findViewById(R.id.ok)
        tv_content?.text = msg
        ok?.setOnClickListener {
            dismiss()
        }
    }

}