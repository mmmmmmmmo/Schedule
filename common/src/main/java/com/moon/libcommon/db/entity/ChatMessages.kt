package com.moon.libcommon.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moon.libcommon.db.MessageType

/**
 * 聊天消息列表详情
 */
@Entity
class ChatMessages(
    var from_imei: String, //消息发送方
    var to_imei: String, //好友接受方
    var msgid: String,//消息ID
    var msgtype: MessageType = MessageType.TEXT,//消息类型
    var chatType: ChatType = ChatType.SINGLE_CHAT,
    var direct: SendType = SendType.SEND,//好友接受放0 发送 ，1 接受
    var msgTime: Long = System.currentTimeMillis(),//消息创建时间
    var status: Status = Status.CREATE
) {
    @PrimaryKey(autoGenerate = true)
    var _id: Long = 0
    var session_id: Long? = null
    var unread = false//是否未读
    var isListened = false//语音信息是否听过
    var data0: String? = null //内容 http://XXX.amr 或 http://XXX.jp,或文本，或表情 10个可扩展的字段,远程的图片下载地址，语音下载地址
    var data1: String? = null //内容 http://XXX.amr 或 http://XXX.jp  默认为语音时间，
    var data2: String? = null //内容 http://XXX.amr 或 http://XXX.jp 语音本地地址
    var data3: String? = null //内容 http://XXX.amr 或 http://XXX.jp
    var data4: String? = null //内容 http://XXX.amr 或 http://XXX.jp
    var data5: String? = null //内容 http://XXX.amr 或 http://XXX.jp
    var data6: String? = null //内容 http://XXX.amr 或 http://XXX.jp
    var data7: String? = null //内容 http://XXX.amr 或 http://XXX.jp
    var data8: String? = null //内容 http://XXX.amr 或 http://XXX.jp
    var data9: String? = null //内容 http://XXX.amr 或 http://XXX.jp

    fun isSend(): Boolean {
        return direct == SendType.SEND
    }
}

//消息类型
enum class ChatType {
    EMPTY,  //占位符
    SINGLE_CHAT, GROUP_CHAT;

    companion object {
        fun valueOf(value: Int): ChatType {
            return when (value) {
                1 -> SINGLE_CHAT
                2 -> GROUP_CHAT
                else -> EMPTY
            }
        }
    }
}

//消息发送状态，发送成功，失败，发送中，创建
enum class Status {
    SUCCESS, FAIL, INPROGRESS, CREATE;

    companion object {
        fun valueOf(value: Int): Status {
            return when (value) {
                0 -> SUCCESS
                1 -> FAIL
                2 -> INPROGRESS
                3 -> CREATE
                else -> CREATE
            }
        }
    }
}

enum class SendType {
    SEND, RECEIVER;

    companion object {
        fun valueOf(value: Int): SendType {
            return when (value) {
                0 -> SEND
                1 -> RECEIVER
                else -> SEND
            }
        }
    }
}