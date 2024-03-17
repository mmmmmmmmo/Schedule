package com.moon.libcommon.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Friend(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var uid: String,
    var account_id: String,
    var name: String?,
    var remark: String?,
    var headerIcon: String?,
    var imei: String//智能机好友的话，imei是手机号虚拟的
)