package com.moon.libcommon.entity

//设备信息
data class DeviceInfo(
    var name: String? = null,//产品名称
    var software: String? = null,//固件版本号
    var hardware: String? = null//硬件版本号
) {

    fun clear() {
        name = null
        software = null
        hardware = null
    }
}

