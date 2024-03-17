package com.moon.libbase.base.db

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import timber.log.Timber


abstract class DBResultObserver<T> : Observer<T> {

    override fun onComplete() {
        onRequestEnd()
    }

    override fun onError(e: Throwable) {
        Timber.d(e)
        onFailure(e)
        onRequestEnd()
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onSubscribe(d: Disposable) {
        if (!d.isDisposed) {
            onRequestStart()
        }
    }

    open fun onRequestStart() {

    }

    open fun onRequestEnd() {

    }
    /**
     * 返回成功
     *
     */
    @Throws(Exception::class)
    abstract fun onSuccess(result: T?)

    /**
     * 返回失败
     */
    @Throws(Exception::class)
    open fun onFailure(e: Throwable){

    }
}
