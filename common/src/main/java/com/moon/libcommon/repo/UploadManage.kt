package com.moon.libcommon.repo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.moon.libbase.utils.system.SystemUtils
import com.moon.libbase.utils.ui.ToastUtils
import com.moon.libcommon.R
import com.moon.libcommon.utils.MMKVManage
import com.moon.libcommon.utils.PathUtils
import com.permissionx.guolindev.PermissionX
import java.io.File

typealias onSelectedFile = (String?) -> Unit

class UploadManage {

    companion object {
        private const val REQUEST_CODE_LOCAL = 1001
        private const val REQUEST_CODE_CAMERA = 1002
        private const val PERMISSION_CAMERA = 10001
    }

    private var cameraFile: File? = null
    private lateinit var host: UploadHost

    var onSelectedFile: onSelectedFile? = null
    var onSelectedFileInterface:((File?)->Unit)?=null

    fun createHost(activity: FragmentActivity) {
        host = ActivityHost(activity)
    }

    fun createHost(fragment: Fragment) {
        host = FragmentHost(fragment)
    }

    /**
     * 从图库获取图片
     */
    fun selectPicFromLocal() {
        val activity = host.getHostActivity() as? FragmentActivity ?: return
        //SD卡权限
        PermissionX.init(activity)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .request { allGranted, _, _ ->
                if (allGranted) {
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        MediaStore.Images.Media.CONTENT_TYPE
                    )
                    host.startActivityForResult(intent, REQUEST_CODE_LOCAL)
                }
            }
    }

    /**
     * OnActivityResult分发逻辑
     */
    fun dispatchOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOCAL) {
                val selectedImage = data?.data
                if (selectedImage == null) {
                    onSelectedFile?.invoke(null)
                    onSelectedFileInterface?.invoke(null)
                } else {
                    onSelectedFile?.invoke(getPicFromUrl(selectedImage))
                    onSelectedFileInterface?.invoke(File(getPicFromUrl(selectedImage)))
                }
                return true
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                if (cameraFile != null && cameraFile!!.exists()) {
                    onSelectedFile?.invoke(cameraFile?.absolutePath)
                    onSelectedFileInterface?.invoke(File(cameraFile?.absolutePath))
                }
            }
        }
        return false
    }

    fun selectPicFromCamera() {
        PermissionX.init(host.getHostActivity())
            .permissions(Manifest.permission.CAMERA)
            .request { allGranted, _, _ ->
                if (allGranted) {
                    if (!SystemUtils.isExitsSdcard()) {
                        val st: String =
                            host.getContext().resources.getString(R.string.sd_card_does_not_exist)
                        ToastUtils.toast(st)
                    }
                    startCamera()
                }
            }
    }

    private fun startCamera() {
        cameraFile =
            File(PathUtils.cameraImagePath, MMKVManage.uid + System.currentTimeMillis() + ".jpg")
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                host.getContext(),
                SystemUtils.getPackageInfo(host.getContext()).packageName + ".provider",
                cameraFile!!
            )
        } else {
            Uri.fromFile(cameraFile)
        }
        host.startActivityForResult(
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
                MediaStore.EXTRA_OUTPUT,
                uri
            ), REQUEST_CODE_CAMERA
        )
    }


    fun getPicFromUrl(selectedImage: Uri): String? {
        var cursor: Cursor? = null
        try {
            cursor = host.getContext().contentResolver.query(
                selectedImage,
                arrayOf(MediaStore.Images.Media.DATA),
                null,
                null,
                null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            val segments = selectedImage.pathSegments
            if (segments.size > 2) {
                val segment = segments[2]
                val uri = Uri.Builder().scheme("content")
                    .authority("media")
                    .path(segment.substring(segment.lastIndexOf("//media") + "//media".length + 1))
                    .build()
                cursor = host.getContext().contentResolver.query(
                    uri,
                    arrayOf(MediaStore.Images.Media.DATA),
                    null,
                    null,
                    null
                )
            }
        }
        val error = "没有找到照片"
        if (cursor != null) {
            cursor.moveToFirst()
            val picturePath = cursor.getString(0)
            cursor.close()
            if (picturePath == null || picturePath == "null") {
                ToastUtils.toast(error)
                return null
            }
            return picturePath
        } else {
            val file = File(selectedImage.path!!)
            if (!file.exists()) {
                ToastUtils.toast(error)
                return null
            }
            return file.absolutePath
        }
    }

}