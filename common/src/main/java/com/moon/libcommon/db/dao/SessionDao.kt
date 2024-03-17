package com.moon.libcommon.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moon.libcommon.db.entity.ChatSession
import com.moon.libcommon.db.entity.SessionAllData

@Dao
interface SessionDao {
    @Insert
    fun insert(session: ChatSession): Long

    @Update
    fun update(session: ChatSession)

    //获取登录账号与指定联系人的会话
    @Query("select * from  ChatSession where account_id=:account and to_id=:toid")
    fun getChatSession(account: String, toid: String): ChatSession?

    //获取所有会话列表
    @Query("select * from  ChatSession where account_id=:account")
    fun getChatSessionList(account: String): List<ChatSession>

    //获取所有会话列表
    @Query("select * from  ChatSession where account_id=:account")
    fun getChatSessionListLD(account: String): LiveData<List<ChatSession>>

    //获取所有会话列表，包含用户信息
    @Transaction
    @Query(
        " select A.*,msgtype,data0,data1, B.*,name,remark,headerIcon from (SELECT ChatSession.*  FROM ChatSession where account_id=:account) as A " +
                "LEFT JOIN CHATMESSAGES ON A.last_msgid = CHATMESSAGES._id " +
                "LEFT JOIN (select friend_head_photo,friend_remark,friend_remark_new,friend_imei from BINDUSERENITY where account=:account) AS B ON A.to_id = B.friend_imei " +
                "LEFT JOIN (select name,remark,headerIcon,uid from Friend where account_id=:account) AS F ON A.to_id = F.uid"
                +" order by A.last_msg_time desc"
    )
    fun getChatSessionFriendListLD(account: String): LiveData<List<SessionAllData>>

    @Query("select SUM(unread_message_counts) from  ChatSession where account_id=:account")
    fun getChatUnReadCount(account: String):Int?

    //获取所有会话未读消息总和
    @Query("select SUM(unread_message_counts) from  ChatSession where account_id=:account")
    fun getChatUnReadCountLD(account: String):LiveData<Int?>

    //获取单独会话未读消息
    @Query("select unread_message_counts from  ChatSession where account_id=:account and to_id=:toid")
    fun getChatUnReadLD(account: String, toid: String):LiveData<Int?>

    @Query("select unread_message_counts from  ChatSession where account_id=:account and to_id=:toid")
    fun getChatUnRead(account: String, toid: String):Int?

    @Transaction
    @Query(
        "select * from ( " +
                "select A.*,CHATMESSAGES.msgtype,CHATMESSAGES.data0,CHATMESSAGES.data1, B.*,F.name,F.remark,F.headerIcon " +
                "from (SELECT ChatSession.*  FROM ChatSession where account_id=:account) as A " +
                "LEFT JOIN CHATMESSAGES ON A.last_msgid = CHATMESSAGES._id " +
                "LEFT JOIN (select friend_head_photo,friend_remark,friend_remark_new,friend_imei from BINDUSERENITY where account=:account ) AS B ON A.to_id = B.friend_imei " +
                "LEFT JOIN (select name,remark ,headerIcon,uid from Friend where account_id=:account) AS F ON A.to_id = F.uid" +
                ") as R where name like :key or remark like :key or friend_remark like :key or friend_remark_new like :key"
    )
    fun searchSession(account: String, key: String): List<SessionAllData>

    @Query("Update  ChatSession SET unread_message_counts=0 where id=:session_id")
    fun setAllRead(session_id: Long)

    @Delete
    fun delete(session: ChatSession)

    @Query("delete  from ChatSession where account_id=:account and to_id=:toid")
    fun delete(account: String, toid: String)

    @Query("delete  from ChatSession where id=:session_id ")
    fun delete(session_id: Long)
}