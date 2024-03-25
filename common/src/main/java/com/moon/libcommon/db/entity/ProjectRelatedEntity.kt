package com.moon.libcommon.db.entity

import androidx.room.PrimaryKey

/**
 * @Description TODO
 * @systemUser Zhuyuandongs
 * @Author
 * @Date 03-25-2024 周一 16:16
 */

data class CommonThing(
    var title:String,
    var type:Int,
    var createTime:Long,
    var description:String?,
    var preThing:Int?,
    var startTime:Long?,
    var scheduleTime:Int?,//单位分
    var client:String?,
    var withBigProject:Long?
){
    @PrimaryKey(autoGenerate = true)
    val id:Long=0
}

data class BigProject(
    var beginThing:Int,
    var endThing:Int,
    var description: String?
){
    @PrimaryKey(autoGenerate = true)
    val id:Long=0
}