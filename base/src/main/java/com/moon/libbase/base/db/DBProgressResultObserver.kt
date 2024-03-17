package com.moon.libbase.base.db

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

/**
 * @author ry
 * @date 2019-11-11
 */
abstract class DBProgressResultObserver<T>(private val dialogStatus: MutableLiveData<Boolean>) : DBResultObserver<T>() {

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