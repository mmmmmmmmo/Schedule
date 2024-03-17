package com.moon.libbase.base.net

import android.accounts.NetworkErrorException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * @author ry
 * @date 2019-12-13
 */
abstract class BaseHttpObserver<T> : Observer<HttpResult<T>> {
    override fun onSubscribe(d: Disposable) {
        if (!d.isDisposed) {
            onRequestStart()
        }
    }

    override fun onNext(response: HttpResult<T>) {
        if (response.ret_code == SUCCESS_CODE) {
            try {
                onSuccess(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                onInnerCodeError(response.ret_code, response.ret_msg ?: "", response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onError(e: Throwable) {
        Timber.e(e, "ResultObserver onError")
        try {
            if (e is ConnectException
                || e is TimeoutException
                || e is SocketTimeoutException
                || e is NetworkErrorException
                || e is UnknownHostException
            ) {
                onFailure(e, true)
            } else {
                onFailure(e, false)
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
        onRequestEnd()
    }

    override fun onComplete() {
        onRequestEnd()
    }

    /**
     * 返回成功了,但是code错误
     *
     * @param code
     * @param message
     * @param result,错误code也可能携带data数据
     * @throws Exception
     */
    @Throws(Exception::class)
    abstract fun onInnerCodeError(code: Int, message: String, result: T?)

    open fun onRequestStart() {

    }

    open fun onRequestEnd() {

    }

    /**
     * 返回成功
     *
     * @param result
     * @throws Exception
     */
    @Throws(Exception::class)
    abstract fun onSuccess(result: T?)

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    @Throws(Exception::class)
    abstract fun onFailure(e: Throwable, isNetWorkError: Boolean)
}