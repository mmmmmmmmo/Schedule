package com.moon.libcommon.paging

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.moon.libbase.base.net.HttpResult
import com.moon.libbase.base.net.NetworkState
import com.moon.libcommon.http.CommonObserver
import io.reactivex.Observable

/**
 * @author ry
 * @date 2019-08-15
 */
abstract class BaseAfterPageDataSource<V> :PageKeyedDataSource<Int, V>(){
    //初次加载的状态
    val initialLoad = MutableLiveData<NetworkState>()
    //加载更多的状态
    val networkState = MutableLiveData<NetworkState>()

    private var retry: (() -> Any)? = null

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            ArchTaskExecutor.getIOThreadExecutor().execute { it.invoke() }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, V>) {
        getLoadInitialList(params).subscribe(object : CommonObserver<List<V>>() {
            override fun onSuccess(result: List<V>?) {
                callback.onResult(result ?: emptyList(),null,1)
                initialLoad.postValue(NetworkState.SUCCESS)
                retry = null
            }
           override fun onInnerCodeError(code: Int, message: String, result: List<V>?) {
               super.onInnerCodeError(code, message, result)
               initialLoad.postValue(NetworkState.NETERROR)
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
    abstract fun getLoadInitialList(params: LoadInitialParams<Int>): Observable<HttpResult<List<V>>>

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, V>) {
        networkState.postValue(NetworkState.LOADING)
        getAfterList(params).subscribe(object : CommonObserver<List<V>>() {
            override fun onSuccess(result: List<V>?) {
                callback.onResult(result ?: emptyList(),params.key + 1)
                networkState.postValue(NetworkState.SUCCESS)
                retry = null
            }

            override fun onFailure(e: Throwable, isNetWorkError: Boolean) {
                if (isNetWorkError) {
                    networkState.postValue(NetworkState.NETERROR)
                } else {
                    val error = NetworkState.error(e.message ?: "unknown error")
                    networkState.postValue(error)
                }
                retry = {
                    loadAfter(params, callback)
                }
            }
        })
    }

    abstract fun getAfterList(params: LoadParams<Int>): Observable<HttpResult<List<V>>>

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, V>) {

    }
}