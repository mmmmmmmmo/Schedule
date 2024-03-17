package com.moon.libcommon.entity

/**
 * @author ry
 * @date 2020-02-25
 */

data class LoginAccount(
    var uid: String,
    var token: String,
    var target: Int,
    var email: String,
    var username: String,
    var icon: String
)

data class LoginResp(
    var phone: String,
    var user_id: String,
    var username: String,
    var Head_photo: String,
    var token:String
)



