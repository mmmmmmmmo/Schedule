package com.moon.libbase.utils.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

/**
 * @author ry
 * @date 2020-01-09
 */


inline fun <X, Y> LiveData<X>.map(crossinline mapFunction: (X) -> Y): MediatorLiveData<Y> {
    val result = MediatorLiveData<Y>()
    result.addSource(this,
        Observer<X> { x -> result.setValue(mapFunction.invoke(x)) })
    return result
}