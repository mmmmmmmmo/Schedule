package com.moon.libcommon.dialog

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class CheckUpdateView constructor(context: Context) : CenterPopupView(context) {
    var clickListener: (() -> Unit)? = null
    var loadimg: ImageView? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_check_update
    }

    override fun onCreate() {
        super.onCreate()
        loadimg = findViewById(R.id.loadimg)
        var anim = AnimationUtils.loadAnimation(context, R.anim.loading)
        loadimg?.startAnimation(anim)
    }
}