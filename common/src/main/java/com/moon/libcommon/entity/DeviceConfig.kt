package com.moon.libcommon.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.moon.libcommon.utils.CHexConvert
import kotlinx.android.parcel.Parcelize

/**
 * 已绑定的所有设备信息
 */
@Parcelize
data class BindDeviceInfo(val logo: String, val nickname: String, val imei: String, val type: Int) : Parcelable

/**
 * imei绑定状态
 */
data class ImeiBindStatus(val status: Boolean, val phone: String?)

/**
 * 绑定申请记录
 */
data class BindRequest(
    val bind_request_id: String,
    val from_id: String,
    val to_id: String,
    val friend_remark: String,//手表昵称
    val from_user_remark: String,//申请人昵称
    val friend_phone: String,
    val from_phone: String,
    val create_time: String,
    val status: String
)

data class BindRequestBoth(val alreadyResolveRequests:List<BindRequest>,val unResolveRequests:List<BindRequest>)

/**
 * 设备健康自动检测配置,单位分钟，0代表配置关闭
 */
@Parcelize
data class DeviceHealthConfig(
    @SerializedName("hearTime") var heartTime: Int?, //心率自动检测设置
    var bloodOxygenTime: Int?, //血氧自动检测设置
    var bloodPressureTime: Int?, // 血压自动检测设置
    var temperatureTime: Int?, // 温度自动检测设置
    var imperial: Int?, //公英制单位设置
    var fahrenheit: Int?, //温度单位
    var heartnotifiy: Int?,//心率异常提醒开关
    val heartnotifiy_low: Int?, //异常最低心率
    val heartnotifiy_high: Int?, //异常最高心率
    val imei: String? = null
) : Parcelable {
    companion object {
        fun parse(value: ByteArray): DeviceHealthConfig {
            val hearTime = CHexConvert.byteToInt(value[0])
            val bloodOxygenTime = CHexConvert.byteToInt(value[1])
            val bloodPressureTime = CHexConvert.byteToInt(value[2])
            val temperatureTime = CHexConvert.byteToInt(value[3])
            val imperial = value[4].toInt()
            val fahrenheit = value[5].toInt()
            val heartNotify = value[6].toInt()
            val heartNotifyLow = CHexConvert.byteToInt(value[7])
            val heartNotifyHigh = CHexConvert.byteToInt(value[8])
            return DeviceHealthConfig(
                hearTime, bloodOxygenTime, bloodPressureTime, temperatureTime,
                imperial, fahrenheit, heartNotify, heartNotifyLow, heartNotifyHigh
            )
        }
    }
}


/**
 * 设备配置 包括型号和支持的健康模块
 * @param type 机型（账号模式返回）
 * @param module 支持的健康模块
 */
data class DeviceConfig(val type: String, val module: List<DeviceModule>?) {
    companion object {
        fun parse(value: ByteArray): DeviceConfig {
            val list = mutableListOf<DeviceModule>()
            val supportHeart = value[0].toInt() == 1
            val supportOxygen = value[0].toInt() == 1
            val supportPressure = value[0].toInt() == 1
            val supportTemperature = value[0].toInt() == 1
            if (supportHeart) {
                list.add(DeviceModule(MODULE_HEART))
            }
            if (supportOxygen) {
                list.add(DeviceModule(MODULE_OXYGEN))
            }
            if (supportPressure) {
                list.add(DeviceModule(MODULE_PRESSURE))
            }
            if (supportTemperature) {
                list.add(DeviceModule(MODULE_TEMPERATURE))
            }

            return DeviceConfig("", list)
        }
    }
}

const val MODULE_HEART = 1
const val MODULE_OXYGEN = 2
const val MODULE_PRESSURE = 3
const val MODULE_TEMPERATURE = 4

/**
 * 健康模块
 * @param module 模块类型 1.心率 2.血氧 3.血压 4.体温
 */
data class DeviceModule(val module: Int)

/**
 * 设备绑定的成员
 * @param type 1管理员 0非管理员
 */
data class DeviceBindMember(val userId: Long, val logo: String?, val nickname: String, val phone: String, val type: Int)


/**
 * 绑定的蓝牙设备信息
 */
@Parcelize
data class BluetoothDeviceInfo(val address: String, val imei: String, val type: Int, var modelName: String) : Parcelable


data class DeviceDetail(
    var logo: String,
    val imei: String,
    var nickname: String,
    var phone: String,
    var sex: Int?,
    var height: Int?,
    var weight: Int?,
    val isAdmin: Boolean,
    var logoLocal: Boolean //标记是读取本地还是网络
)

