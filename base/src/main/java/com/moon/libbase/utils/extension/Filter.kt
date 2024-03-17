package com.moon.libbase.utils.extension

import com.moon.libbase.utils.FilterUtils

/**
 * @author ry
 * @date 2019-06-29
 */
/**
 * 验证一个字符串是否是手机形式
 */
fun String?.isPhone(): Boolean {
    if (this.isNullOrBlank()) {
        return false
    }
    return FilterUtils.isPhone(this)
}

fun String?.isMacAddress(): Boolean {
    if (this.isNullOrBlank()) {
        return false
    }
    return this.matches(Regex("^[\\w]{2}:[\\w]{2}:[\\w]{2}:[\\w]{2}:[\\w]{2}:[\\w]{2}$"))
}

fun String?.isEmail(): Boolean {
    if (this.isNullOrBlank()) {
        return false
    }
    return this.matches(Regex("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
}

fun String?.isAccountName(): Boolean {
    if (this.isNullOrBlank()) {
        return false
    }
    return this.matches(Regex("^[A-Za-z0-9]{1,10}$"))
}

fun String?.isPassword(): Boolean {
    if (this.isNullOrBlank()) {
        return false
    }
    //return this.matches(Regex("^[A-Za-z0-9]{5,15}$"))
    return this.matches(Regex("^(?![0-9]+\$)(?![a-zA-Z]+\$)[0-9A-Za-z]{6,16}$"))
}

fun String?.isImei(): Boolean {
    if (this.isNullOrBlank()) {
        return false
    }
    return this.matches(Regex("^[0-9]{14,16}$"))
}

fun String?.starPhoneNumber(): String? {
    val sb = StringBuffer()
    return if (this!!.length > 10) {
        val frontThreeString = this.substring(0, 3)
        sb.append(frontThreeString)
        val substring = this.substring(3, 7)
        val replace = substring.replace(substring, "****")
        sb.append(replace)
        val lastFourString = this.substring(7, 11)
        sb.append(lastFourString)
        sb.toString()
    } else {
        ""
    }
}