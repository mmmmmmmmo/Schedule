package com.moon.libcommon.db.dao

import androidx.room.*
import com.moon.libcommon.db.entity.Friend

@Dao
interface FriendDao {
    @Insert
    fun insert(friend: Friend): Long

    @Update
    fun update(friend: Friend)

    //获取登录账号与指定联系人的会话
    @Query("select * from  Friend where account_id=:account and uid=:uid")
    fun getFriend(account: String, uid: String): Friend?

    //获取所有会话列表
    @Query("select * from  Friend where account_id=:account")
    fun getFriendList(account: String): List<Friend>

    @Delete
    fun delete(friend: Friend)

    @Query("delete  from Friend where account_id=:account and uid=:uid")
    fun delete(account: String, uid: String)
}