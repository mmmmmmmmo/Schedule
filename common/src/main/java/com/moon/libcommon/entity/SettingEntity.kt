package com.moon.libcommon.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Setting(
    var status :Int?=null
):Parcelable{
    fun getStatus():Boolean{
        return status==1
    }

}