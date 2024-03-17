package com.moon.libcommon.entity

//修改绑定用户信息，返回的数据结构，跟返回绑定列表是不一样的，醉了
data class BabyInfoUpdateResult(
    val bluetooth_address: String,
    val friend_id: String,
    val from_user_remark: String,
    val from_user_type: String,
    val head_photo: String,
    val phone: String,
    val remark: String,
    val role_type: String,
    val short_phone: String,
    val user_id: String,
    var sex: Int?,
    var age: Int?,
    var height: Int?,
    var weight: Int?
)