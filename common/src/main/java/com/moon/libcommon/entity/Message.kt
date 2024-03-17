package com.moon.libcommon.entity

import java.util.*

data class Message(
    var message_id: String,
    var title: String,
    var content: String,
    var create_time: String,
    var icon:String,
    var user_type:String,//1为管理员，2为非管理员
    var message_type:String,
)