package com.moon.libcommon.dialog

import android.content.Context
import android.widget.Button
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class LogOutView constructor(context: Context) : CenterPopupView(context) {
    var clickListener: (() -> Unit)? = null
    var cancel: Button? = null
    var logout: Button? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_log_out
    }

    override fun onCreate() {
        super.onCreate()
        cancel = findViewById(R.id.cancel)
        logout = findViewById(R.id.logout)
        cancel?.setOnClickListener {
            dismiss()
        }
        logout?.setOnClickListener {
            clickListener?.invoke()
            dismiss()
        }
    }
}