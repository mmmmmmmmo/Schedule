package com.moon.libbase.base

import com.moon.libbase.base.net.BaseHttpObserver
import com.moon.libbase.base.net.HttpResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author ry
 * @date 2019-05-16
 */
open class BaseRepo {

    /**
     * 执行请求返回结果
     */
    fun <T> executeResult(observable: Observable<HttpResult<T>>, subscriber: BaseHttpObserver<T>) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }


}