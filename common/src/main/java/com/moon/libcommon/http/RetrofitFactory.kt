package com.moon.libcommon.http

import android.os.Build
import android.util.Log
import com.moon.libbase.BuildConfig
import com.moon.libbase.utils.secret.Base32
import com.moon.libbase.utils.secret.Md5Util
import com.moon.libcommon.utils.MMKVManage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author ry
 * @date 2019-05-10
 */
class RetrofitFactory constructor(okHttpClient: OkHttpClient) {


    private val headerInterceptor: Interceptor = Interceptor { chain ->
        val original = chain.request()
        val originalHttpUrl = original.url()
        // val token = MMKVManage.token
        val paths = originalHttpUrl.pathSegments()
        if (paths.contains("sendImage") || paths.contains("sendAudio") || paths.contains("sendText")
            ||(paths.contains("vedio")&&paths.contains("push"))) {
            chain.proceed(chain.request())
        } else {
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("systemVer", Build.VERSION.RELEASE)
                .addQueryParameter("phone_brand", Build.MANUFACTURER)
                .addQueryParameter("device", "Android")
                .addQueryParameter("key", "olderDesktop")
                .addQueryParameter("userkey", "1")
//            .addQueryParameter("ver", BuildConfig.VERSION_CODE.toString())
                .addQueryParameter("phone_model", Build.MODEL)
                .addQueryParameter("site_code", "Noain")
                .addQueryParameter("project_code", "Noain")
                .addQueryParameter("l_device_id", "")
                .addQueryParameter("l_phone", "")
                .addQueryParameter("time", System.currentTimeMillis().toString())
//                .addQueryParameter("device_id", MMKVManage.getLastToken())//不晓得不固定会有啥影响
//                .addQueryParameter("device_token", MMKVManage.getLastToken())
                .addQueryParameter("platform", "0")
                .addQueryParameter("app_ver_code", "1000")
            val value = url.build().queryParameterValues("phone")
            if (value.size > 0) {
                url.addQueryParameter("imei", value[0] + "0000")//通过手机号，构建imei
                    .addQueryParameter("imsi", value[0] + "0000")//通过手机号，构建imsi
            } else {
                url.addQueryParameter("imei", MMKVManage.getFakeIMEI())//通过手机号，构建imei
                    .addQueryParameter("imsi", MMKVManage.getFakeIMEI())//通过手机号，构建imsi
            }


            /*   val parameter = originalHttpUrl.queryParameter("uid")
               if (parameter.isNullOrEmpty()) {
                   val uId: String = MMKVManage.uid
                   url.addQueryParameter("uid", uId)
               }*/

            val names = url.build().queryParameterNames()
            val auth = StringBuffer()
            val token = StringBuffer()
            names.forEach {
                if ("" == auth.toString()) {
                    auth.append(it)
                } else {
                    auth.append(",$it")
                }
                val value = url.build().queryParameterValues(it)
                token.append(value[0])
            }
            Log.d("wlf", "token:$token ")
            url.addQueryParameter("auth", Base32.encode(auth.toString().toByteArray()))
            url.addQueryParameter("token", Md5Util.generateStr(token.toString()))

            val request = original.newBuilder()
//            .addHeader("token", token)
                .url(url.build())
            chain.proceed(request.build())
        }
    }


    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            okHttpClient.newBuilder()
//                .addInterceptor(SignInterceptor())
                .addInterceptor(headerInterceptor)
                .build()
        )
        .build()
}