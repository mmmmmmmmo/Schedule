package com.moon.libcommon.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserInfo : Parcelable {
    val uid: Long = 0
    val token: String? = null
    var logo: String? = null //头像url
    var nickname: String? = null//昵称,默认为手机号后4位
}