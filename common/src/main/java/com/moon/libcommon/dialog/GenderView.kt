package com.moon.libcommon.dialog

import android.content.Context
import android.widget.ImageView
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

class GenderView constructor(context: Context) : CenterPopupView(context) {
    var isWoman: Int = 1
    var clickListener: ((Int) -> Unit)? = null
    var man: ImageView? = null
    var woman: ImageView? = null
    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_gender
    }

    override fun onCreate() {
        super.onCreate()
        man = findViewById(R.id.man)
        woman = findViewById(R.id.woman)

        man?.setOnClickListener {
            isWoman = 0
            clickListener?.invoke(isWoman)
            dismiss()
        }
        woman?.setOnClickListener {
            isWoman = 1
            clickListener?.invoke(isWoman)
            dismiss()
        }
    }
}