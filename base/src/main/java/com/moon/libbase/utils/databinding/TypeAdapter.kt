package com.moon.libbase.utils.databinding

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

// _ooOoo_  
// o8888888o  
// 88" . "88  
// (| -_- |)  
//  O\ = /O  
// ___/`---'\____  
// .   ' \\| |// `.  
// / \\||| : |||// \  
// / _||||| -:- |||||- \  
// | | \\\ - /// | |  
// | \_| ''\---/'' | |  
// \ .-\__ `-` ___/-. /  
// ___`. .' /--.--\ `. . __  
// ."" '< `.___\_<|>_/___.' >'"".  
// | | : `- \`.;`\ _ /`;.`/ - ` : | |  
// \ \ `-. \_ __\ /__ _/ .-` / /  
// ======`-.____`-.___\_____/___.-`____.-'======  
// `=---='  
//          .......................  
//           佛曰：bug泛滥，我已瘫痪！

/**
 * 基本数据类型转换
 * TeachAssistant
 * create by NiMa.Wang on 2020/1/11
 */
@InverseMethod("convertString2Long")
fun convertLong2String(value: Long?): String {
    if (value == null) {
        return ""
    }
    return value.toString()
}

fun convertString2Long(string: String?): Long? {
    if (string.isNullOrBlank()) {
        return null
    }
    return try {
        string.toLong()
    } catch (e: NumberFormatException) {
        null
    }
}

@InverseMethod("convertString2Float")
fun convertFloat2String(value: Float?): String {
    if (value == null) {
        return ""
    }
    return value.toString()
}

fun convertString2Float(string: String?): Float? {
    if (string.isNullOrBlank()) {
        return null
    }
    return try {
        string.toFloat()
    } catch (e: NumberFormatException) {
        null
    }
}

@InverseMethod("convertFloat2Int")
fun convertInt2Float(value: Int?): Float {
    if (value == null) {
        return 0f
    }
    return value.toFloat()
}

fun convertFloat2Int(value: Float): Int? {
    return value.toInt()
}
