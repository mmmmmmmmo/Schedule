package com.moon.libcommon.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.moon.libcommon.db.entity.BindUserEnity


@Dao
interface BindUserDao {
    @Insert
    fun insert(bindUser: BindUserEnity): Long

    @Insert
    fun insert(bindUser: List<BindUserEnity>): List<Long>

    @Update
    fun update(bindUser: BindUserEnity)

    //获取登录账号与指定联系人
    @Query("select * from BindUserEnity where account=:account and friend_id=:uid")
    fun getBindUserEnityLD(account: String, uid: String): LiveData<BindUserEnity?>

    //获取登录账号与指定联系人
    @Query("select * from BindUserEnity where account=:account and friend_id=:uid")
    fun getBindUserEnity(account: String, uid: String): BindUserEnity?

    @Query("select * from BindUserEnity where account=:account and friend_imei=:imei")
    fun getBindUserEnitybyImei(account: String, imei: String): BindUserEnity?

    @Query("select * from  BindUserEnity where account=:account and (friend_remark like:key or friend_remark_new like :key)")
    fun searchBindUserList(account: String, key: String): List<BindUserEnity>

    @Query("select * from  BindUserEnity where account=:account")
    fun getBindUserEnityListLD(account: String): LiveData<List<BindUserEnity>>

    @Query("update BindUserEnity set friend_remark_new =:remark where account=:account and friend_id=:uid")
    fun setRemark(account: String, uid: String, remark: String)

    @Query("update BindUserEnity set isadmin =:isAdmin where account=:account and friend_id=:uid")
    fun setIsAdmin(account: String,uid: String,isAdmin:Int)

    @Delete
    fun delete(bindUser: BindUserEnity)

    //删除指定账号 account 下的，friend_id为uid的绑定用户信息
    @Query("delete from BindUserEnity where account=:account and friend_id=:uid")
    fun delete(account: String, uid: String)

    //删除指定账号 account 下的绑定关系
    @Query("delete from BindUserEnity where account=:account")
    fun delete(account: String)
}