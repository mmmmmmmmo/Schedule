package com.moon.libcommon.entity

import com.google.gson.annotations.SerializedName
import com.moon.libcommon.utils.CHexConvert

data class HomeHealthData(
    val status: Boolean,
    @SerializedName("stepall") val stepAll: Int?, //总步数
    val target: Int?,//目标值
    @SerializedName("heartrate") val heartRate: Int?,//心率值
    @SerializedName("bloodoxygen") val bloodOxygen: Int?,//血氧值
    val high: Int?,//血压-高压
    val low: Int?,//血压-低压
    @SerializedName("bodytemperature") val bodyTemperature: Int?,//体温
    val power: Int? //电量
) {

    companion object {
        fun parse(status: Boolean, byte: ByteArray): HomeHealthData? {
            if (byte.size != 15) return null
            val step = CHexConvert.bytesToInt(byte, 0, 4)
            val targetStep = CHexConvert.bytesToInt(byte, 4, 4)
            val heart = byte[8].toInt()
            val oxygen = byte[9].toInt()

            val pressureHigh = byte[10].toInt()
            val pressureLow = byte[11].toInt()

            val temperature = CHexConvert.bytesToInt(byte, 12, 2)

            val power = byte[14].toInt()

            return HomeHealthData(status, step, targetStep, heart, oxygen, pressureHigh, pressureLow, temperature, power)
        }
    }
}

