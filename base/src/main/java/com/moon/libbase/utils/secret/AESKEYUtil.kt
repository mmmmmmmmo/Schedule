package com.moon.libbase.utils.secret

import android.util.Log
import timber.log.Timber
import kotlin.experimental.and

class AESKEYUtil {

    companion object {
        fun key2String(bytes: ByteArray): String {
            val result = StringBuilder()
            for (b in bytes) {
                val st = String.format("%02X", b)
                result.append(st)
            }
            return result.toString()
        }

        fun string2Key(str: String): ByteArray {
            if (str.length % 2 != 0) {
                Timber.e("不符合格式")
            }
            val result = str.chunked(2).map {
                it.toInt(16).toByte()
            }.toByteArray()
            return result
        }
    }
}