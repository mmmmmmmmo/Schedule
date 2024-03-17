package com.moon.libbase.base.net

import androidx.lifecycle.Observer
import com.moon.libbase.base.callback.DialogCallback

/**
 * @author ry
 * @date 2020-01-08
 */
class DialogObserver(val callback: DialogCallback) : Observer<Boolean> {
    override fun onChanged(status: Boolean) {
        if (status){
            callback.showDialog()
        }else{
            callback.dismissDialog()
        }
    }

}