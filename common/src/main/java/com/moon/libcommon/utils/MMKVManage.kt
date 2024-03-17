package com.moon.libcommon.utils

import android.os.Build
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.moon.libbase.BuildConfig
import com.moon.libcommon.db.entity.BindUserEnity
import com.moon.libcommon.entity.UserInfo
import com.tencent.mmkv.MMKV
import java.util.*

object MMKVManage {

    private val mmkv = MMKV.defaultMMKV()
    private const val KEY_FIRST_LAUNCH = "key_first_launch" //是否首次启动
    const val KEY_UID = "uid" //uid
    const val KEY_USER_NAME = "user"
    const val KEY_HEAD_PHOTO = "head_photo"
    const val KEY_BIND_ID = "bind_id"
    const val KEY_PSW = "psw"//密码
    const val KEY_TOKEN = "token" //token,TCP登录后返回的token值
    const val KEY_AGREEMENT = "key_agreement" //首页权限提示
    const val KEY_SPLASHGUIDE = "key_splashguide" //引导页显示
    const val KEY_PHONE = "key_phone" //登录完成存储手机号
    const val KEY_USERINFO = "key_userinfo" //登录完成存储手机号
    const val KEY_FRIEND_ID = "friend_id"
    const val KEY_BIND_LIST = "key_bind_list"//当前账号绑定的用户（设备）列表的缓存
    const val KEY_CURRENT_BIND = "key_current_bind"//当前账号的设备信息
    const val KEY_CITY_CODE = "city_code"
    const val KEY_AD_STATUS = "ad_status";//新版广告开关
    const val KEY_FIRST_SETUP = "first_setup"
    const val KEY_PUSH_SOUND = "push_sound"//声音
    const val KEY_PUSH_VIBRATE = "push_vibrate"//震动
    const val KEY_NEW_REQUEST = "new_request"//申请
    const val KEY_NEW_MESSAGE = "new_message"//消息
    const val KEY_NOTICE_START = "notice_start"//点击通知启动
    const val KEY_NOTICE_IMEI = "notice_imei"//点击通知启动
    const val KEY_NOTICE_ID = "notice_id"//推送註冊返回id
    const val KEY_NOTICE_UP = "notice_up"//申请id向服务端推送
    const val KEY_SUG_SEARCH = "sug_search"//搜索历史

    const val KEY_DEVICE_TOKEN = "key_device_token"//设备token，唯一
    const val KEY_LOGIN_TOKEN = "key_login_token"//登录返回token，用于长链接登录

    const val KEY_TCP_WATCH_FRIEND = "watch_friend"//push300推送手表新的好友申请
    const val KEY_TCP_FENCE_WARN = "fence_warn"//push300推送手表新的围栏报警


    //TCP连接使用的 地址和端口
    const val KEY_TCP_URL = "tcp_url"
    const val KEY_TCP_PORT = "tcp_port"

    //服务端 的AES key 和IV
    const val KEY_AES_KEY = "aes_key"
    const val KEY_AES_IV = "aes_iv"

    /**
     * 接口区分常量
     */
    //验证码码类型
    const val TYPE_REGISTER = 1
    const val TYPE_FORGET = 2//忘记、修改
    const val TYPE_PHONE = 3

    var token: String
        set(value) {
            mmkv.encode(KEY_TOKEN, value)
        }
        get() = mmkv.decodeString(KEY_TOKEN) ?: ""
    var phone: String
        set(value) {
            mmkv.encode(KEY_PHONE, value)
        }
        get() = mmkv.decodeString(KEY_PHONE) ?: ""

    var login_token: String
        set(value) {
            mmkv.encode(KEY_LOGIN_TOKEN, value)
        }
        get() = mmkv.decodeString(KEY_LOGIN_TOKEN) ?: ""

    /**
     * 获取IMEI，模拟唯一性，在手机号之后加4位，服务器后台需要获取设备IMEI来查找用户，假装这个是台设备
     */
    fun getFakeIMEI(): String {
        return mmkv.decodeString(KEY_PHONE) + "0000"
    }

    var uid: String
        set(value) {
            mmkv.encode(KEY_UID, value)
        }
        get() = mmkv.decodeString(KEY_UID) ?: ""

    fun clearUserInfo() {
        mmkv.remove(KEY_UID)
        mmkv.remove(KEY_PHONE)
        mmkv.remove(KEY_USER_NAME)
        mmkv.remove(KEY_HEAD_PHOTO)
        mmkv.remove(KEY_TOKEN)
        mmkv.remove(KEY_DEVICE_TOKEN)
        mmkv.remove(KEY_TCP_FENCE_WARN)
        mmkv.remove(KEY_TCP_WATCH_FRIEND)
    }

    var agreement: Boolean
        set(value) {
            mmkv.encode(KEY_AGREEMENT, value)
        }
        get() = mmkv.decodeBool(KEY_AGREEMENT, false)

    var splashGuide: Boolean
        set(value) {
            mmkv.encode(KEY_SPLASHGUIDE, value)
        }
        get() = mmkv.decodeBool(KEY_SPLASHGUIDE, false)

    var userInfo: UserInfo?
        set(value) {
            mmkv.encode(KEY_USERINFO, value)
        }
        get() = mmkv.decodeParcelable(KEY_USERINFO, UserInfo::class.java)

    /**
     * 密码
     */
    var psw: String
        set(value) {
            mmkv.decodeString(KEY_PSW, value)
        }
        get() = mmkv.decodeString(KEY_PSW) ?: " "

    /**
     * 昵称
     */
    var username: String
        set(value) {
            mmkv.encode(KEY_USER_NAME, value)
        }
        get() = mmkv.decodeString(KEY_USER_NAME) ?: ""

    /**
     * 头像
     */
    var headPhoto: String
        set(value) {
            mmkv.encode(KEY_HEAD_PHOTO, value)
        }
        get() = mmkv.decodeString(KEY_HEAD_PHOTO) ?: ""

    /**
     * 当前绑定人的id
     */
    var bind_id: String
        set(value) {
            mmkv.encode(KEY_BIND_ID, value)
        }
        get() = mmkv.decodeString(KEY_BIND_ID) ?: ""

    /**
     * friend_id
     */
    /*  var friend_id: String
          set(value) {
              mmkv.encode(KEY_FRIEND_ID, value)
          }
          get() = mmkv.decodeString(KEY_FRIEND_ID) ?: ""*/


    /**
     * 设置当前的绑定的设备信息
     */
    fun setCurrentBind(bind: BindUserEnity) {
        bind_id = bind.friend_id
        mmkv.encode(KEY_CURRENT_BIND, bind)
    }

    /**
     * 当前的绑定的设备信息
     */
    fun getCurrentBind(): BindUserEnity? {
        return mmkv.decodeParcelable(KEY_CURRENT_BIND, BindUserEnity::class.java)
    }

    fun getCurrentImei(): String {
        return if (getCurrentBind() != null) {
            getCurrentBind()!!.friend_imei
        } else {
            ""
        }
    }

    fun clearCurrentBind() {
        mmkv.remove(KEY_BIND_ID)
        mmkv.remove(KEY_CURRENT_BIND)
    }

    /**
     * 城市id
     */
    var city_code: String
        set(value) {
            mmkv.encode(KEY_CITY_CODE, value)
        }
        get() = mmkv.decodeString(KEY_CITY_CODE) ?: ""

    /**
     * 新版广告开关
     */
    var ad_status: HashMap<String, Int>
        set(value) {
            mmkv.encode(KEY_AD_STATUS, Gson().toJson(value))
        }
        get() = Gson().fromJson<HashMap<String, Int>>(
            mmkv.decodeString(KEY_AD_STATUS),
            object : TypeToken<HashMap<String, Int>>() {}.type
        )

    fun isSplashAdShow(): Boolean {
        val data: String = mmkv.decodeString(KEY_AD_STATUS) ?: ""
        return if (TextUtils.isEmpty(data)) {
            false
        } else {
            val hashMap = Gson().fromJson<HashMap<String?, Int?>>(
                data,
                object : TypeToken<HashMap<String?, Int?>>() {}.type
            )
            val status = hashMap["splash"]!!
            status == 1
        }
    }

    var first_setup: Boolean
        set(value) {
            mmkv.encode(KEY_FIRST_SETUP, value)
        }
        get() = mmkv.decodeBool(KEY_FIRST_SETUP, false)


    var host: String
        set(value) {
            mmkv.encode(KEY_TCP_URL, value)
        }
        get() = mmkv.decodeString(KEY_TCP_URL) ?: BuildConfig.PUSH_URL
    var port: Int
        set(value) {
            mmkv.encode(KEY_TCP_PORT, value)
        }
        get() = mmkv.decodeInt(KEY_TCP_PORT, BuildConfig.PUSH_PORT)


    var key: ByteArray?
        set(value) {
            mmkv.encode(KEY_AES_KEY, value)
        }
        get() = mmkv.decodeBytes(KEY_AES_KEY)
    var iv: ByteArray?
        set(value) {
            mmkv.encode(KEY_AES_IV, value)
        }
        get() = mmkv.decodeBytes(KEY_AES_IV)

    var push_sound: Boolean
        set(value) {
            mmkv.encode(KEY_PUSH_SOUND, value)
        }
        get() = mmkv.decodeBool(KEY_PUSH_SOUND, true)

    var push_vibrate: Boolean
        set(value) {
            mmkv.encode(KEY_PUSH_VIBRATE, value)
        }
        get() = mmkv.decodeBool(KEY_PUSH_VIBRATE, true)


    fun getDeviceToken(): String {
        var deviceToken = mmkv.getString(KEY_DEVICE_TOKEN, "")
        if (deviceToken.isNullOrEmpty()) {
            deviceToken = getDeviceUuid()
            setDeviceToken(deviceToken)
        }
        return deviceToken
    }

    /**
     * 获取最新token，如果厂商推送返回id为空，设为随机token
     */
    fun getLastToken(): String{
        var token = mmkv.getString(KEY_NOTICE_ID, "")
        if(token.isNullOrEmpty()){
            token =  getDeviceToken()
        }
        return token
    }

    fun setDeviceToken(toekn: String) {
        mmkv.encode(KEY_DEVICE_TOKEN, toekn)
    }

    fun getDeviceUuid(): String {
        val devIDShort =
            "35" + Build.BOARD.length % 10 + Build.BRAND.length % 10
        +Build.DEVICE.length % 10 + Build.DISPLAY.length % 10
        +Build.HOST.length % 10
        +Build.ID.length % 10 + Build.MANUFACTURER.length % 10
        +Build.MODEL.length % 10 + Build.PRODUCT.length % 10
        +Build.TAGS.length % 10 + Build.TYPE.length % 10
        +Build.USER.length % 10 // 13 位
        var serial = "serial" + System.currentTimeMillis() + Random().nextInt(10000000)
        return UUID(devIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
    }

    var newRequest: Int
        set(value) {
            mmkv.encode(KEY_NEW_REQUEST, value)
        }
        get() = mmkv.decodeInt(KEY_NEW_REQUEST, 0)

    var newMessage: Int
        set(value) {
            mmkv.encode(KEY_NEW_MESSAGE, value)
        }
        get() = mmkv.decodeInt(KEY_NEW_MESSAGE, 0)

    var noticeStart: Boolean
        set(value) {
            mmkv.encode(KEY_NOTICE_START, value)
        }
        get() = mmkv.decodeBool(KEY_NOTICE_START, false)

    var noticeImei: String?
        set(value) {
            mmkv.encode(KEY_NOTICE_IMEI, value)
        }
        get() = mmkv.decodeString(KEY_NOTICE_IMEI, "")

    var noticeId: String
        set(value) {
            mmkv.encode(KEY_NOTICE_ID, value)
        }
        get() = mmkv.decodeString(KEY_NOTICE_ID)?:""

    var noticeUp: Boolean
        set(value) {
            mmkv.encode(KEY_NOTICE_UP, value)
        }
        get() = mmkv.decodeBool(KEY_NOTICE_UP, false)

    var sugList: String?
        set(value) {
            mmkv.encode(KEY_SUG_SEARCH, value)
        }
        get() = mmkv.decodeString(KEY_SUG_SEARCH, "")

    /**
     * 记录围栏报警
     */

    fun setFenceWarn(map: HashMap<Int, Int>) {
        mmkv.encode(KEY_TCP_FENCE_WARN, Gson().toJson(map))
    }

    fun getFenceWarn():HashMap<Int, Int>{
        val fence = mmkv.decodeString(KEY_TCP_FENCE_WARN)
        if(fence.isNullOrBlank()){
            val map = HashMap<Int, Int>()
            setFenceWarn(map)
        }
        return Gson().fromJson<HashMap<Int, Int>>(
            mmkv.decodeString(KEY_TCP_FENCE_WARN),
            object : TypeToken<HashMap<Int, Int>>() {}.type
        )
    }

    fun setWatchFriend(map: HashMap<Int, Int>) {
        mmkv.encode(KEY_TCP_WATCH_FRIEND, Gson().toJson(map))
    }

    fun getWatchFriend():HashMap<Int, Int>{
        val request = mmkv.decodeString(KEY_TCP_WATCH_FRIEND)
        if(request.isNullOrBlank()){
            val map = HashMap<Int, Int>()
            setWatchFriend(map)
        }
        return Gson().fromJson<HashMap<Int, Int>>(
            mmkv.decodeString(KEY_TCP_WATCH_FRIEND),
            object : TypeToken<HashMap<Int, Int>>() {}.type
        )
    }

    fun addFenceWarn(watchId:Int,count:Int){
        var warnMap = getFenceWarn()
        warnMap[watchId] = count
        setFenceWarn(warnMap)
    }

    fun reMoveFenceWarn(watchId:Int){
        var warnMap = getFenceWarn()
        if(warnMap.containsKey(watchId)){
            warnMap.remove(watchId)
        }
        setFenceWarn(warnMap)
    }

    fun addWatchFriend(watchId:Int){
        var requestList = getWatchFriend()
        requestList[watchId] = 1
        setWatchFriend(requestList)
    }

    fun reMoveWatchFriend(watchId:Int){
        var requestList = getWatchFriend()
        if(requestList.containsKey(watchId)){
            requestList.remove(watchId)
        }
        setWatchFriend(requestList)
    }
}