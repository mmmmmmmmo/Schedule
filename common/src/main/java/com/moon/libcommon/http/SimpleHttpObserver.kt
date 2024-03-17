package com.moon.libcommon.http

class SimpleHttpObserver : CommonObserver<String>() {
    override fun onSuccess(result: String?) {
    }

    override fun onError(e: Throwable) {

    }

    override fun onFailure(e: Throwable, isNetWorkError: Boolean) {
        
    }
}