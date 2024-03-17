package com.moon.libcommon.paging

import androidx.annotation.WorkerThread
import com.moon.libbase.base.net.HttpResult
import com.moon.libbase.base.net.NetworkState
import com.moon.libcommon.http.CommonObserver
import io.reactivex.Observable

/**
 * @author ry
 * @date 2019-05-30
 */
abstract class BaseBeforeDataSource<K, V> : BaseItemKeyedDataSource<K, V>() {

    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<V>) {
    }

    abstract fun getBeforeList(params: LoadParams<K>): Observable<HttpResult<List<V>>>

    @WorkerThread
    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<V>) {
        networkState.postValue(NetworkState.LOADING)
        getBeforeList(params).subscribe(object : CommonObserver<List<V>>() {
            override fun onSuccess(result: List<V>?) {
                callback.onResult(result?: emptyList())
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
                    loadBefore(params, callback)
                }
            }
        })
    }


}