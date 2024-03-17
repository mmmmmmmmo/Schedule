package com.zhu.gptproj.entity

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 03-14-2024 周四 16:14
 */
data class ProjInCalendar(
    val projId:Long=0,//0表示未入数据库，空白项目
    val title:String,
    val description:String
)
