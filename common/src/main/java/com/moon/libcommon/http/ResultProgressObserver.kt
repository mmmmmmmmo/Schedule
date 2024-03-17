package com.moon.libcommon.http

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

/**
 * @author ry
 * @date 2019-05-30
 */
abstract class ResultProgressObserver<T>(private val dialogStatus: MutableLiveData<Boolean>) : CommonObserver<T>() {

    @MainThread
    override fun onRequestStart() {
        super.onRequestStart()
        dialogStatus.value = true
    }

    @MainThread
    override fun onRequestEnd() {
        super.onRequestEnd()
        dialogStatus.value = false
    }
}