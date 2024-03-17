package com.moon.libcommon.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moon.libbase.utils.DateUtils
import com.moon.libbase.utils.TimeUtils
import java.util.*

/**
 * 会话数据表
 */
@Entity
class ChatSession(
    var account_id: String,//账户id，对应某个账户的会话列表
    var to_id: String,//和谁的聊天
    var chatType: ChatType = ChatType.SINGLE_CHAT//聊天类型
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var last_msgid: Long? = null //最后一条消息的id
    var remind_type: Int = 0 //提醒类型 0，提醒 1，不提醒即免打扰
    var message_counts: Int = 0 // 消息数量
    var unread_message_counts: Int = 0//未读消息数量
    var last_msg_time: Long? = null  //最后一条消息时间


    fun lastMsgTime(): String {
        last_msg_time?.let {
            return TimeUtils.getTimeStr(it)
        }
        return ""
    }

    fun lastMsg(): String {

        return ""
    }
}