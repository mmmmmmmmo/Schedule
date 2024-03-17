package com.moon.libcommon.http

import androidx.lifecycle.MutableLiveData
import com.moon.libbase.base.net.NetworkState

/**
 * @author ry
 * @date 2019-12-24
 */
abstract class NetworkStateObserver<T>(
    private val netStatus: MutableLiveData<NetworkState>
) : CommonObserver<T>() {
    var refresh: Boolean = false

    constructor (
        netStatus: MutableLiveData<NetworkState>,
        refresh: Boolean = false
    ) : this(netStatus) {
        this.refresh = refresh
    }

    override fun onRequestStart() {
        super.onRequestStart()
        netStatus.value = if (refresh) NetworkState.LOADING_REFRESH else NetworkState.LOADING
    }

    override fun onSuccess(result: T?) {
        if (result == null) {
            netStatus.value = if (refresh) NetworkState.EMPTY_REFRESH else NetworkState.EMPTY
        } else if (result is List<*>) {
            if (result.isEmpty()) {
                netStatus.value = if (refresh) NetworkState.EMPTY_REFRESH else NetworkState.EMPTY
            } else {
                netStatus.value =
                    if (refresh) NetworkState.SUCCESS_REFRESH else NetworkState.SUCCESS
            }
        } else {
            netStatus.value = if (refresh) NetworkState.SUCCESS_REFRESH else NetworkState.SUCCESS
        }
    }

    override fun onFailure(e: Throwable, isNetWorkError: Boolean) {
        super.onFailure(e, isNetWorkError)
        netStatus.value = if (refresh) NetworkState.FAILED_REFRESH else NetworkState.FAILED
    }

    override fun onInnerCodeError(code: Int, message: String, result: T?) {
        super.onInnerCodeError(code, message, result)
        netStatus.value = if (refresh) NetworkState.FAILED_REFRESH else NetworkState.FAILED
    }


}