package com.moon.libcommon.dialog

import android.content.Context
import android.widget.Button
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class ForceUpdateView constructor(context: Context) : CenterPopupView(context) {
    var clickListener: (() -> Unit)? = null
    var ok: Button? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_forceupdate_view
    }

    override fun onCreate() {
        super.onCreate()
        ok = findViewById(R.id.ok)
        ok?.setOnClickListener {
            clickListener?.invoke()
        }
    }
}