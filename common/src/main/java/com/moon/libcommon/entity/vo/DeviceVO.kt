package com.moon.libcommon.entity.vo


/**
 * 页面需要的设备封装类
 */
data class HomeDeviceInfo(val imei: String, val name: String, val log: String?)

/**
 * @param type 修改的设置类型 [UnitTimeSettingCmd]
 */
data class HealthTimeSetting(val type: Int, val value: Int)

data class UnitSetting(val mileageUnit: Int, val temperatureUnit: Int)