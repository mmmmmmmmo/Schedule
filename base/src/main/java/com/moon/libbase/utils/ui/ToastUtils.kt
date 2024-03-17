package com.moon.libbase.utils.ui

import android.app.Application
import androidx.annotation.MainThread
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

/**
 * @author ry
 * @date 2019-05-15
 */
object ToastUtils {

    var application: Application? = null

    fun init(application: Application) {
        ToastUtils.application = application
    }

    @MainThread
    fun toast(id: Int) {
        application?.toast(id)
    }

    @MainThread
    fun toast(text: String) {
        application?.toast(text)
    }

    @MainThread
    fun toast(id: Int, vararg formatArgs: Any) {
        val text = application?.getString(id, *formatArgs)
        if (!text.isNullOrEmpty()) {
            toast(text)
        }
    }

    @MainThread
    fun longToast(id: Int) {
        application?.longToast(id)
    }

    @MainThread
    fun longToast(text: String) {
        application?.longToast(text)
    }
}