package com.moon.libcommon.paging

import androidx.annotation.WorkerThread
import com.moon.libbase.base.net.HttpResult
import com.moon.libbase.base.net.NetworkState
import com.moon.libcommon.http.CommonObserver
import io.reactivex.Observable

/**
 * @author ry
 * @date 2019-05-22
 */
abstract class BaseAfterDataSource<K, V> : BaseItemKeyedDataSource<K, V>() {




    @WorkerThread
    override fun loadAfter(params: LoadParams<K>, callback: LoadCallback<V>) {
        networkState.postValue(NetworkState.LOADING)
        getAfterList(params).subscribe(object : CommonObserver<List<V>>() {
            override fun onSuccess(result: List<V>?) {
                callback.onResult(result ?: emptyList())
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

    abstract fun getAfterList(params: LoadParams<K>): Observable<HttpResult<List<V>>>

    override fun loadBefore(params: LoadParams<K>, callback: LoadCallback<V>) {

    }


}