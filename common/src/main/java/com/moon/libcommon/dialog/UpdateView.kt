package com.moon.libcommon.dialog

import android.content.Context
import android.widget.Button
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class UpdateView constructor(context: Context) : CenterPopupView(context) {
    var clickListener: (() -> Unit)? = null
    var cancelListener : (() -> Unit)? = null
    var cancel: Button? = null
    var ok: Button? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_update_view
    }

    override fun onCreate() {
        super.onCreate()

        cancel = findViewById(R.id.cancel)
        ok = findViewById(R.id.ok)
        cancel?.setOnClickListener {
            dismiss()
        }
        ok?.setOnClickListener {
            clickListener?.invoke()
            dismiss()
        }

        cancel?.setOnClickListener {
            cancelListener?.invoke()
            dismiss()
        }
    }
}