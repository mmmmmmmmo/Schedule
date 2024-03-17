package com.moon.libcommon.repo

import com.moon.libbase.base.BaseRepo
import com.moon.libbase.base.net.HttpResult
import com.moon.libbase.utils.bitmap.CompressPicture
import com.moon.libbase.utils.extension.ioScheduler
import com.moon.libbase.utils.secret.Md5FileNameGenerator
import com.moon.libcommon.http.ApiConfig
import com.moon.libcommon.http.CommonObserver

import com.moon.libcommon.utils.PathUtils
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import java.io.File
import java.net.URLEncoder
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author ry
 * @date 2019-07-05
 * 上传资源
 */
@Singleton
class CommonRepo @Inject constructor(retrofit: Retrofit) : BaseRepo() {

    private val api = retrofit.create(CommonApi::class.java)

    fun uploadResZip(files: List<File>, resultObserver: CommonObserver<List<UploadRes>>) {
        Observable.create<ArrayList<File>> {
            val uploadFiles = ArrayList<File>(files.size)
            files.forEach {
                val generator = Md5FileNameGenerator()
                val mdFileName = generator.generate(it.absolutePath)

                val savePath = PathUtils.cameraImagePath + mdFileName + ".jpg"
                CompressPicture.instance()
                    .compressWithBounds(savePath, it.absolutePath, 768f, 1280f)
                uploadFiles.add(File(savePath))
            }
            it.onNext(uploadFiles)
            it.onComplete()
        }.flatMap {
            return@flatMap uploadRes(it)
        }.ioScheduler().subscribe(resultObserver)
    }

    fun uploadResZip_SameName(files: List<File>, resultObserver: CommonObserver<List<UploadRes>>) {
        Observable.create<ArrayList<File>> {
            val uploadFiles = ArrayList<File>(files.size)
            files.forEach {
                var content = URLEncoder.encode(it.getName(), "UTF-8")
                var savePath = PathUtils.thumbImagePath + content
                CompressPicture.instance()
                    .compressWithBounds(savePath, it.getAbsolutePath(), 768f, 1280f)
                uploadFiles.add(File(savePath))
            }
            it.onNext(uploadFiles)
            it.onComplete()
        }.flatMap {
            return@flatMap uploadRes(it)
        }.ioScheduler().subscribe(resultObserver)
    }

    fun uploadRes(files: List<File>, resultObserver: CommonObserver<List<UploadRes>>) {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        files.forEach {
            builder.addFormDataPart(
                "img",
                it.name,
                RequestBody.create(MediaType.parse("application/octet-stream"), it)
            )
        }
        val requestBody = builder.build()
        api.uploadRes(ApiConfig.RES_UPLOAD_URL, requestBody).ioScheduler().subscribe(resultObserver)
    }

    fun uploadRes(files: List<File>): Observable<HttpResult<List<UploadRes>>> {
        val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        files.forEach {
            builder.addFormDataPart(
                "img",
                it.name,
                RequestBody.create(MediaType.parse("application/octet-stream"), it)
            )
        }
        val requestBody = builder.build()
        return api.uploadRes(ApiConfig.RES_UPLOAD_URL, requestBody)
    }
}

data class UploadRes(var fileName: String, var resName: String)

