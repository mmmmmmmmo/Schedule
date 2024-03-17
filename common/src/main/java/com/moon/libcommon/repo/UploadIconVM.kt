package com.moon.libcommon.repo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moon.libbase.utils.bitmap.CompressPicture
import com.moon.libbase.utils.secret.Md5FileNameGenerator
import com.moon.libcommon.http.ResultProgressObserver
import com.moon.libcommon.utils.PathUtils
import java.io.File
import javax.inject.Inject

/**
 * @author ry
 * @date 2020-01-14
 */
class UploadIconVM @Inject constructor(val resRepo: CommonRepo) : ViewModel() {

    val progress = MutableLiveData<Boolean>()
    val uploadUrl = MutableLiveData<String>()

    val uploadList = MutableLiveData<List<String>>()

    fun uploadIcon(filePath: String) {
        val uploadFiles = ArrayList<File>(1)
        val generator = Md5FileNameGenerator()
        val mdFileName = generator.generate(filePath) + ".jpg"
        val savePath = PathUtils.cameraImagePath + mdFileName
        //压缩图片上传
        CompressPicture.instance()
            .compressWithBounds(savePath, filePath, 768f, 1280f)
        uploadFiles.add(File(savePath))
        resRepo.uploadRes(uploadFiles, object : ResultProgressObserver<List<UploadRes>>(progress) {
            override fun onSuccess(result: List<UploadRes>?) {
                if (!result.isNullOrEmpty()) {
                    result.forEach {
                        if (it.fileName == mdFileName) {
                            uploadUrl.value = it.resName
                        }
                    }
                }
            }
        })
    }

    fun uploadImg(files: List<File>) {
        resRepo.uploadResZip(files, object : ResultProgressObserver<List<UploadRes>>(progress) {
            override fun onSuccess(result: List<UploadRes>?) {
                if (!result.isNullOrEmpty()) {
                    val urlList = mutableListOf<String>()
                    result.forEach {
                        urlList.add(it.resName)
                    }
                    uploadList.value = urlList
                }
            }
        })
    }

}