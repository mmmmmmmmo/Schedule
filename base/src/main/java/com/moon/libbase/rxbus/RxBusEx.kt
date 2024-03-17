package com.moon.libbase.rxbus

import androidx.lifecycle.LifecycleOwner
import com.moon.libbase.utils.extension.autoClear
import io.reactivex.functions.Consumer

inline fun <reified T> LifecycleOwner.rxBus(action: Consumer<T>) {
    RxBus.get().toIOSubscribe(T::class.java, action).autoClear(this)
}