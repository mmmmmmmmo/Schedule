package com.moon.libcommon.dialog

import android.content.Context
import com.lxj.xpopup.core.BottomPopupView
import com.moon.libcommon.R

class SelectDateView constructor(context: Context): BottomPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_update_view
    }

    override fun onCreate() {
        super.onCreate()
    }
}