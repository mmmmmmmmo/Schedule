package com.moon.libbase.base.net

import androidx.lifecycle.Observer
import com.moon.libbase.base.callback.LoadingCallBack

/**
 * @author ry
 * @date 2020-01-08
 * lifecycle Observer
 */
class NetStateObserver(private val refreshLayout: LoadingCallBack) : Observer<NetworkState> {

    private var enableLoading = true

    override fun onChanged(it: NetworkState) {
        if (enableLoading || it.refresh) {
            when (it.status) {
                Status.LOADING -> refreshLayout.showLoading()
                Status.SUCCESS -> {
                    refreshLayout.showContent()
                    enableLoading = false
                }
                Status.EMPTY -> refreshLayout.showEmpty()
                Status.FAILED, Status.NETERROR -> refreshLayout.showRetry()
            }
        }
        if (it.status != Status.LOADING) {
            refreshLayout.onFinish()
        }
    }


}