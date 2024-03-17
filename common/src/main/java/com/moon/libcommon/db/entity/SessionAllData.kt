package com.moon.libcommon.db.entity

import com.moon.libbase.base.BaseApplication
import com.moon.libbase.utils.TimeUtils
import com.moon.libcommon.R
import com.moon.libcommon.db.MessageType

/**
 * to_id 可能对应 相同friend_imei 的不同账户下的绑定用户或不同账户下的好友uid一样的用户，这里可以返回列表到下一层去过滤相同account的数据出来
 * 现在改成视图形式获取会话数据，考虑到后面还有群组等功能，同一个用户被不同本机账户绑定，或称为好友等情况
 */
/*class SessionAllData(
    @Embedded
    var session: ChatSession,
    @Relation(
        parentColumn = "to_id", //聊天关系中，账号为imei，不是用户id
        entityColumn = "friend_imei"
    )
    var bindUserEnity: BindUserEnity?,

    @Relation(
        parentColumn = "to_id", //聊天关系中，账号为imei，不是用户id
        entityColumn = "uid"
    )
    var friend: Friend?
)*/

data class SessionAllData(
    var account_id: String,//账户id，对应某个账户的会话列表
    var to_id: String,//和谁的聊天
    var chatType: ChatType = ChatType.SINGLE_CHAT//聊天类型
) {
    var id: Long = 0
    var last_msgid: Long? = null //最后一条消息的id
    var remind_type: Int = 0 //提醒类型 0，提醒 1，不提醒即免打扰
    var message_counts: Int = 0 // 消息数量
    var unread_message_counts: Int = 0//未读消息数量
    var last_msg_time: Long? = null  //最后一条消息时间

    //好友关系数据
    var name: String? = null
    var remark: String? = null
    var headerIcon: String? = null

    //绑定关系数据（后面不存在绑定关系表的对应，只有好友跟群组，暂时好友关系来源是绑定，上面的好友关系是假的）
    var friend_imei: String? = null
    var friend_head_photo: String? = null
    var friend_remark: String? = null
    var friend_remark_new: String? = null

    var msgtype: MessageType = MessageType.TEXT//消息类型
    var data0: String? = null//消息数据
    var data1: String? = null//消息数据

    fun lastMsgTime(): String {
        last_msg_time?.let {
            return TimeUtils.getTimeStr(it)
        }
        return ""
    }

    fun showName(): String {
        return friend_remark_new ?: friend_remark ?: remark ?: name?:to_id
    }

    fun lastMsg(): String {
        if (msgtype == MessageType.TEXT) {
            return data0 ?: ""
        } else if (msgtype == MessageType.AUDIO) {
            return BaseApplication.instance.getString(R.string.voice)
        } else if (msgtype == MessageType.IMAGE) {
            return BaseApplication.instance.getString(R.string.picture)
        }
        return ""
    }
}