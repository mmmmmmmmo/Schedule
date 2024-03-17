package com.moon.libbase.base.callback

/**
 * @author ry
 * @date 2019-12-24
 */
interface LoadingCallBack {
    fun showContent()
    fun showLoading()
    fun showRetry()
    fun showEmpty()
    fun onFinish()
}