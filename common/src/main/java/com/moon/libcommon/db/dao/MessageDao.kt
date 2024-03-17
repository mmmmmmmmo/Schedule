package com.moon.libcommon.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moon.libcommon.db.entity.ChatMessages

@Dao
interface MessageDao {
    @Insert()
    fun insert(message: ChatMessages): Long

    @Update
    fun update(message: ChatMessages)

    @Query("select * from ChatMessages where session_id=:session_id  order by _id Desc limit :count")
    fun getMessagesBySession(session_id: Long, count: Int): LiveData<List<ChatMessages>>

    @Query("select * from ChatMessages where msgId=:msgId")
    fun getMessagesBymsgId(msgId: Long): ChatMessages

    @Query("delete  from ChatMessages where msgid=:msg_id")
    fun delete(msg_id: String)

    @Query("delete from ChatMessages where session_id=:session_id")
    fun delete(session_id: Long)

    @Query("Update  ChatMessages SET isListened = 1 where session_id=:session_id and msgid=:msgid")
    fun setListened(session_id: Long,msgid:String)
}