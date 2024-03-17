package com.moon.libbase.base.net

/**
 * @author ry
 * @date 2019-05-16
 */
data class HttpResult<T>(var ret_code: Int, var ret_msg: String? = "", var data: T?)


const val SUCCESS_CODE = 0




