package com.moon.libcommon.http

import com.moon.libcommon.utils.MMKVManage
import okhttp3.*
import org.apache.commons.codec.digest.DigestUtils
import timber.log.Timber

/*
class SignInterceptor : Interceptor {


    companion object {
        const val MD5_SALT = "4fd2a4e6286e"

        fun signMapParam(map: Map<*, *>): Map<*, *> {
            val signString = StringBuilder()
            val token = MMKVManage.token
            if (token.isNotEmpty()) {
                signString.append("token$token")
            }
            map.forEach {
                if (it.value != null) {
                    val key = it.key
                    val value = it.value
                    signString.append("$key$value")
                }
            }
            signString.append(MD5_SALT)
            Timber.d(signString.toString())
            val sign = DigestUtils.md5Hex(signString.toString())
            val newMap = map.toMutableMap()
            newMap["sign"] = sign
            return newMap
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val token = MMKVManage.token

        val newRequest = original.newBuilder()
        val newUrl = originalHttpUrl.newBuilder()
        if (original.method() == "GET" || original.method() == "DELETE") {
            val sign = signGetParameter(originalHttpUrl, token)
            newUrl.addQueryParameter("sign", sign)
        }
        newRequest.url(newUrl.build())
        return chain.proceed(newRequest.build())
    }

    private fun signGetParameter(url: HttpUrl, token: String): String {
        val names = url.queryParameterNames()
        val signString = StringBuilder()
        if (token.isNotEmpty()) {
            signString.append("token$token")
        }
        names.forEach {
            if (it.equals("start") || it.equals("end")) {
            } else {
                val value = url.queryParameterValues(it)
                if (value.size > 0) {
                    signString.append("$it${value[0]}")
                }
            }
        }
        signString.append(MD5_SALT)
        Timber.d(signString.toString())
        return DigestUtils.md5Hex(signString.toString())
    }
}*/
