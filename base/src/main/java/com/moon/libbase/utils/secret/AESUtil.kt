package com.moon.libbase.utils.secret

import android.util.Base64
import android.util.Log
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * AES加解密
 */
class AESUtil {
    companion object {
        var currentKey: ByteArray? = null//当前生成的key和向量
        var currentIV: ByteArray? = null

        /**
         * @param keySize  128位（16字节）、192位（24字节）或256位（32字
         */
        fun generateAESKey(keySize: Int): ByteArray {
            var keyBytes: ByteArray
            try {
                val keyGenerator: KeyGenerator = KeyGenerator.getInstance("AES")
                keyGenerator.init(keySize)
                val secretKey: SecretKey = keyGenerator.generateKey()
                keyBytes = secretKey.encoded
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                //没有找到算法，就手动随机生成
                keyBytes = when (keySize) {
                    128 -> generatorKeyBytes(16)
                    192 -> generatorKeyBytes(24)
                    256 -> generatorKeyBytes(32)
                    else -> generatorKeyBytes(16)
                }
            }
            currentKey = keyBytes
            return keyBytes
        }

        /**
         * 随机生产Key
         */
        fun generatorKeyBytes(blockSize: Int): ByteArray {
            val random = Random()
            val ivParam = ByteArray(blockSize)
            for (index in 0 until blockSize) {
                ivParam[index] = random.nextInt(256).toByte()
            }
            return ivParam
        }

        /**
         * 随机生产向量iv
         */
        fun generatorIvBytes(blockSize: Int): ByteArray {
            val random = Random()
            val ivParam = ByteArray(blockSize)
            for (index in 0 until blockSize) {
                ivParam[index] = random.nextInt(256).toByte()
            }
            currentIV = ivParam
            return ivParam
        }

        fun encrypt(data: ByteArray, keyBytes: ByteArray, ivBytes: ByteArray): ByteArray {

            val secretKeySpec = SecretKeySpec(keyBytes, "AES")
            try {
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                val ivParameterSpec = IvParameterSpec(ivBytes)
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
                //加密数据
                /*val resultBytes = cipher.doFinal(data)
                //结果用Base64转码

                var result = Base64.encodeToString(resultBytes, Base64.DEFAULT)
                Log.d("wlf", "encrypt: $result")*/
                return cipher.doFinal(data)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: NoSuchPaddingException) {
                e.printStackTrace()
            } catch (e: InvalidKeyException) {
                e.printStackTrace()
            } catch (e: BadPaddingException) {
                e.printStackTrace()
            } catch (e: IllegalBlockSizeException) {
                e.printStackTrace()
            }
            return data
        }


        fun decryptData(data: ByteArray, keyBytes: ByteArray, ivBytes: ByteArray): ByteArray? {
            var clearData: ByteArray? = null
            val secretKeySpec = SecretKeySpec(keyBytes, "AES")
            try {
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                val ivParameterSpec = IvParameterSpec(ivBytes)
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
//                //先Base64解码
//                val temp = Base64.decode(data, Base64.DEFAULT)
                //解密数据
                clearData = cipher.doFinal(data)
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: NoSuchPaddingException) {
                e.printStackTrace()
            } catch (e: InvalidKeyException) {
                e.printStackTrace()
            } catch (e: BadPaddingException) {
                e.printStackTrace()
            } catch (e: IllegalBlockSizeException) {
                e.printStackTrace()
            } catch (e: InvalidAlgorithmParameterException) {
                e.printStackTrace()
            }
            return clearData
        }
    }


}