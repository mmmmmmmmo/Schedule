package com.moon.libcommon.utils.extension

import com.google.gson.Gson
import com.moon.libbase.utils.bitmap.CompressPicture
import com.moon.libbase.utils.secret.Md5FileNameGenerator
import com.moon.libcommon.utils.PathUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @author ry
 * @date 2019-12-23
 */
@JvmField
val TYPE_PROTO = MediaType.parse("application/x-protobuf")

@JvmField
val TYPE_JSON = MediaType.parse("application/json")

@JvmField
val TYPE_TEXT = MediaType.parse("text/plain;charset=utf-8")
fun String.toRequestBody(): RequestBody {
    return RequestBody.create(TYPE_JSON, this)
}

fun String.toTextRequestBody(): RequestBody {
    return RequestBody.create(TYPE_TEXT, this)
}

fun Any.toJsonRequestBody(gson:Gson): RequestBody {
    /*  if (this is Map<*, *>) { 这个项目不需要这样子签名
          val signMap = SignInterceptor.signMapParam(this)
          return gson.toJson(signMap).toRequestBody()
      }*/
    return gson.toJson(this).toRequestBody()

}

fun packageFormData(path: String?, map: Map<String, String>): MultipartBody {
    val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
    if (!path.isNullOrEmpty()) {
        val file = compressFile(path)
        builder.addFormDataPart(
            "logo",
            file.name,
            RequestBody.create(MediaType.parse("application/octet-stream"), file)
        )
    }
    /* 这个项目不需要这样子签名
    val params = SignInterceptor.signMapParam(map) as Map<String, String>
     params.forEach {
         builder.addFormDataPart(it.key, it.value)
     }*/
    return builder.build()
}

private fun compressFile(filePath: String): File {
    val generator = Md5FileNameGenerator()
    val mdFileName = generator.generate(filePath) + ".jpg"
    val savePath = PathUtils.cameraImagePath + mdFileName
    CompressPicture.instance()
        .compressWithBounds(savePath, filePath, 768f, 1280f)
    return File(savePath)
}