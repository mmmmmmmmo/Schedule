package com.moon.libcommon.paging

import androidx.annotation.WorkerThread
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.moon.libbase.base.net.HttpResult
import com.moon.libbase.base.net.NetworkState
import com.moon.libcommon.http.CommonObserver
import io.reactivex.Observable

/**
 * @author ry
 * @date 2019-12-24
 */
abstract class BaseItemKeyedDataSource<K, V> : ItemKeyedDataSource<K, V>() {

    //初次加载的状态
    val initialLoad = MutableLiveData<NetworkState>()
    //加载更多的状态
    val networkState = MutableLiveData<NetworkState>()

    protected var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            ArchTaskExecutor.getIOThreadExecutor().execute { it.invoke() }
        }
    }

    @WorkerThread
    override fun loadInitial(params: LoadInitialParams<K>, callback: LoadInitialCallback<V>) {
        getLoadInitialList(params).subscribe(object : CommonObserver<List<V>>() {
            override fun onSuccess(result: List<V>?) {
                callback.onResult(result ?: emptyList())
                if (result.isNullOrEmpty()) {
                    initialLoad.postValue(NetworkState.EMPTY)
                } else {
                    initialLoad.postValue(NetworkState.SUCCESS)
                }
                retry = null
            }

            override fun onInnerCodeError(code: Int, message: String, result: List<V>?) {
                super.onInnerCodeError(code, message, result)
                initialLoad.postValue(NetworkState.error(message))
            }

            override fun onFailure(e: Throwable, isNetWorkError: Boolean) {
                if (isNetWorkError) {
                    initialLoad.postValue(NetworkState.NETERROR)
                } else {
                    val error = NetworkState.error(e.message ?: "unknown error")
                    initialLoad.postValue(error)
                }
                retry = {
                    loadInitial(params, callback)
                }
            }

        })
    }

    abstract fun getLoadInitialList(params: LoadInitialParams<K>): Observable<HttpResult<List<V>>>


}