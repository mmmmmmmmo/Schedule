package com.moon.libcommon.entity

import java.util.*

data class StepData(
    var stepid: Long,
    var imei: String,
    var step: Int,
    var stepall: Int,
    var daytime: Date,
    var hour: Int,
    var target: Int,
)

data class HeartData(
    var id: Long,
    var imei: String,
    var indexp: Int,
    var datanum: Int,
    var daytime: Date,
    var time: Date,
)

data class HeartDataResp(
    var average: Int,
    var max: Int,
    var min: Int,
    var dataList: List<HeartData>?,
)

data class BloodOxyData(
    var id: Long,
    var imei: String,
    var datanum: Int,
    var indexp: Int,
    var daytime: Date,
    var time: Date,
)

data class BloodOxyDataResp(
    var average: Int,
    var max: Int,
    var min: Int,
    var dataList: List<BloodOxyData>?,
)

data class BloodPressureData(
    var id: Long,
    var imei: String,
    var high: Int,
    var low: Int,
    var indexp: Int,
    var daytime: Date,
    var time: Date,
)

data class BloodPressureResp(
    var highMax: Int,
    var highMin: Int,
    var lowMax: Int,
    var lowMin: Int,
    var dataList: List<BloodPressureData>?,
)

data class TempData(
    var id: Long,
    var imei: String,
    var datanum: Int,
    var indexp: Int,
    var daytime: Date,
    var time: Date,
)

data class TempDataResp(
    var average: Int,
    var max: Int,
    var min: Int,
    var dataList: List<TempData>?,
)