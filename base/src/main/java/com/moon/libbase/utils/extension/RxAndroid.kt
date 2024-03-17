package com.moon.libbase.utils.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @author ry
 * @date 2019-05-16
 */
fun <T> Observable<T>.ioScheduler(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

/**
 * 用于回调也是在子线程的操作
 */
fun <T> Observable<T>.ioOnioScheduler(): Observable<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
}

fun Completable.ioScheduler(): Completable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.defSubscribe(): Disposable {
    return subscribe({}, {
        Timber.d(it)
    })
}

fun Disposable.autoClear(owner: LifecycleOwner) {
    owner.lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_DESTROY) {
                this@autoClear.dispose()
                owner.lifecycle.removeObserver(this)
                //Timber.d("autoClear:$owner")
            }
        }
    })
}