package com.moon.libcommon.db

import androidx.lifecycle.LiveData
import androidx.room.Entity
import com.moon.libcommon.utils.MMKVManage
import com.moon.libcommon.db.entity.BindUserEnity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
/**
 * 绑定关系的数据库读取走这里，后期可能要跟好友融合
 */
class BindUserUtils @Inject constructor() {

    @Inject
    lateinit var db: MessageDB

    fun cleanBIndUser(account: String) {
        CoroutineScope(Dispatchers.IO).launch {
            db.bindUserDao.delete(MMKVManage.uid)
        }
    }

    fun cleanBindUserById(friend_id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            db.bindUserDao.delete(MMKVManage.uid,friend_id)
        }
    }

    fun setRemark(uid: String, remark: String) {
        CoroutineScope(Dispatchers.IO).launch {
            db.bindUserDao.setRemark(MMKVManage.uid, uid, remark)
        }
    }

    fun refreshBindUser(account: String, list: List<BindUserEnity>) {
        CoroutineScope(Dispatchers.IO).launch {
            list.forEach {
                it.apply {
                    this.account = account
                }
            }
            db.bindUserDao.delete(account)
            db.bindUserDao.insert(list)
        }
    }

    fun updateBindUser(entity: BindUserEnity) {
        CoroutineScope(Dispatchers.IO).launch {
            db.bindUserDao.update(entity)
        }
    }


    fun getBindUserLD(account: String, uid: String): LiveData<BindUserEnity?> {
        return db.bindUserDao.getBindUserEnityLD(account, uid)
    }

    fun getBindUserListLD(account: String): LiveData<List<BindUserEnity>> {
        return db.bindUserDao.getBindUserEnityListLD(account)
    }

    suspend fun searchBindUserList(account: String, key: String): List<BindUserEnity> {
        return withContext(Dispatchers.IO) {
            db.bindUserDao.searchBindUserList(account, key)
        }
    }

    suspend fun getBindUser(account: String, uid: String): BindUserEnity? {
        return withContext(Dispatchers.IO) {
            db.bindUserDao.getBindUserEnity(account, uid)
        }
    }
    suspend fun getBindUserByIMEI(account: String, imei: String): BindUserEnity? {
        return withContext(Dispatchers.IO) {
            db.bindUserDao.getBindUserEnitybyImei(account, imei)
        }

    }

    fun setBindUserAdmin(uid:String,isAdmin:Int){
        CoroutineScope(Dispatchers.IO).launch {
            db.bindUserDao.setIsAdmin(MMKVManage.uid, uid, isAdmin)
        }
    }

}