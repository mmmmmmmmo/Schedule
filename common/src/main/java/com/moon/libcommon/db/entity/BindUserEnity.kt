package com.moon.libcommon.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class BindUserEnity(
    var friend_id: String,
    var bluetooth_address: String? = "",
    var friend_head_photo: String? = "",
    var friend_imei: String,
    var friend_phone: String,
    @SerializedName("friend_name")
    var friend_remark: String,//被绑定者，手表，管理者给他设置的昵称或备注
    @SerializedName("friend_remark")
    var friend_remark_new: String?,//原有的remark 相当于昵称，这个是备注，shit
    var friend_short_phone: String?,//短号
    @SerializedName("from_user_remark")
    var remark: String,//管理者对他而言的备注或者昵称
    @SerializedName("from_user_type")
    var isadmin: Int,//是否管理员 1管理员  2 其他绑定人
    var role_type: Int,
    var sex: Int?,
    var age: Int?,
    var height: Int?,
    var weight: Int?,
    var sensor: Int?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var _id: Long = 0
    var account: String = "" //绑定关系，属于哪个账户的


    fun isAdmin(): Boolean {
        return isadmin == 1
    }

    @Ignore
    fun showName(): String {
        return friend_remark_new ?: friend_remark
    }

    @Ignore
    fun setFieldIndexBy(name: String) {
        if (friend_remark_new != null) {
            friend_remark_new = name
        } else {
            friend_remark = name
        }
    }


}