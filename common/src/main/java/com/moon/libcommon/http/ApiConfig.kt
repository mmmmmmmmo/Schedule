package com.moon.libcommon.http

import android.content.Context
import com.moon.libbase.BuildConfig

/**
 * @author ry
 * @date 2019-05-10
 * 网络请求常量
 */
class ApiConfig {


    companion object {
        //没有id返回的默认值
        const val NO_ID = -1L

        const val TOKEN_BAD = 24 //token失效
        const val TOKEN_ERROR = 401 //token错误
        const val SERVER_ERROR_CODE = 500 //服务端异常
        const val LACK_PARAMETER_CODE = 400//缺少参数

        @Deprecated("暂时没有这个需求")
        const val BUSY_CODE = 3001//系统繁忙，由于验证码请求过多
        const val TOKEN_LOGIN_ERROR = 4006 //token 登录错误
        const val ACCOUNT_NOT_EXIST_CODE = 4001//用户未注册
        const val ERROR_CODE = 4004//验证码错误
        const val ACCOUNT_EXIST_CODE = 4003//用户已注册
        const val PWD_ERROR_CODE = 4002//账户或密码错误

        const val TARGET_SET_ERROR = 5001//目标值设置错误

        const val CODE_FUNC_EMAIL = 2 //请求邮箱验证码


        const val BIND_REPEAT = 104  //已绑定
        const val BIND_ADMIN_VERIFY = 103  //绑定需要管理员同意
        const val USER_NOT_EXIST = 101  //用户不存在

        const val CHANGE_MANGER_REFUSE = 105  //转让管理员还有未处理消息
        const val BIND_REQUEST_REPEAT = 106  //重复提交绑定申请
        const val BIND_IMEI_ERROR = 107  //绑定设备不存在
        const val WATCH_BIND_PHONE = 108  //手表绑定手机报错
        const val WATCH_BIND_ALREADY = 109  //手表已是好友
        const val WATCH_UNABLE = 110  //手表是禁用状态
        const val WATCH_EMOJI_FAILED = 111  //发送表情不支持
        const val WATCH_EMOJI_NOT_ALLOW = 112  //发送表情不支持,加数据库报错
        const val TEST_WATCH_NOT_ONLINE = 113  //测试手表不在线

        //登录类型
        const val LOGIN_TYPE_PWD = 1  //邮箱和密码
        const val LOGIN_TYPE_TOKEN = 2  //token登录

        // 协议类型
        const val TERMS = 1  //用户协议
        const val PRIVACY = 2  //隐私政策

        const val DEVICE_TYPE_MANAGE = 1 // 管理员

        const val GENDER_MALE = 1
        const val GENDER_FEMALE = 0

        const val CMD_TYPE_NUMBER = 1//普通指令类型
        const val CMD_TYPE_REAL = 2//实时指令类型

        const val BIND_SCAN_TYPE = 1//扫码绑定
        const val BIND_MANUAL_TYPE = 2//手动绑定

        const val SUBJECT_MORNING = 0//课程表上午
        const val SUBJECT_AFTERNOON = 1//下午
        const val SUBJECT_NIGHT = 2//晚上

        fun getUpdateUrl(): String {
            return BuildConfig.UPDATE_URL + "tiho/apk/api/update"
        }

        fun getDownLoadUrl(resName: String): String {
            return BuildConfig.UPDATE_URL + "tiho/apk/api/download/" + resName
        }

        const val RES_UPLOAD_URL =
            BuildConfig.BASE_URL + "ximu/cervical/api/resource/upload"
        const val RES_URL_PRE = BuildConfig.BASE_URL + "image/"
        const val FEEDBACK_URL = BuildConfig.FEEDBACK_URL + "ximu/api/feedback"
        fun getResource(resName: String?): String {
            if (resName.isNullOrEmpty()) return ""
            if (resName.startsWith("http")) {
                return resName
            }
            return RES_URL_PRE + resName
        }
    }

}


