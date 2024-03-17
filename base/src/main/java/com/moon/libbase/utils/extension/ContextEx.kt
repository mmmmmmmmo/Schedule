package com.moon.libbase.utils.extension

import android.app.Dialog
import android.content.Context
import android.view.KeyEvent
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
 * @author ry
 * @date 2020-01-10
 */
fun Context.getColorCompat(@ColorRes id: Int):Int {
    return ContextCompat.getColor(this, id)
}

/**
 * dialog点击外部区域是否可取消，同时屏蔽返回键
 * @param cancel true 可取消 ，false 不可取消
 */
fun Dialog?.setOutsideCancel(cancel: Boolean = true){
    this?.setCanceledOnTouchOutside(cancel)
    this?.setCancelable(cancel)
    this?.setOnKeyListener { _, keyCode, _ ->
        if (!cancel) {
            keyCode == KeyEvent.KEYCODE_BACK
        } else {
            false
        }
    }
}