package com.moon.libcommon.db.dao

import androidx.room.Insert
import com.moon.libcommon.db.entity.Friend

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 03-25-2024 周一 16:08
 */
interface GtdDao {
    @Insert
    fun insert(friend: Friend): Long
}