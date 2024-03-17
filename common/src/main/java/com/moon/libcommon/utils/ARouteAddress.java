package com.moon.libcommon.utils;

/**
 * @author ry
 * @date 2019-12-16
 */
public class ARouteAddress {
    /*account模块*/

    public static final String ACCOUNT_ACT_LOGIN = "/account/LoginActivity";
    public static final String ACCOUNT_REGISTER = "/account/RegisterActivity";
    public static final String ACCOUNT_FORGET_PWD = "/account/ForgetPwdActivity";
    public static final String EXTRA_FORGET_PWD_TYPE = "forgetPwdType";//跳转类型

    public static final String ABOUT_ACTIVITY = "/account/AboutActivity";
    public static final String SERVICE_ACTIVITY = "/app/ServiceActivity";
    public static final String ACCOUNT_ACTIVITY = "/account/AccountActivity";
    public static final String ACCOUNT_INFO_ACTIVITY = "/account/AccountInfoActivity";
    public static final String EXTRA_INFO_STYLE = "info_style";
    public static final String ACCOUNT_NAME_ACTIVITY = "/account/AccountNameActivity";
    public static final String CHANGE_PASSWORD_ACTIVITY = "/account/ChangePassWordActivity";
    public static final String CHANGE_PHONE_ACTIVITY = "/account/ChangePhoneActivity";
    public static final String FEED_BACK_ACTIVITY = "/account/FeedBackActivity";
    public static final String ACCOUNT_HELP_ACTIVITY = "/account/HelpActivity";
    public static final String ACCOUNT_SCORE_ACTIVITY = "/account/ScoreActivity";
    public static final String SMS_VERIFICATION_ACTIVITY = "/account/SmsVerificationActivity";
    public static final String ACCOUNT_NOTICE_ACTIVITY = "/account/NoticeActivity";
    public static final String ACCOUNT_MESSAGE_ACTIVITY = "/account/MessageActivity";
    public static final String CONTACT_ACTIVITY = "/app/ContactActivity";
    public static final String EXTRA_NAME = "name";
    public static final String DEVICE_INFO = "/app/DeviceInformationActivity";
    public static final String EXTRA_CHANGE_TYPE = "extra_change_type";
    public static final String EXTRA_CERTIFICATE = "extra_certificate";
    public static final String APP_TEST = "/app/TestActivity";
    public static final String APP_SOS = "/app/SosSettingActivity";


    public static final String EXTRA_URL = "url";
    public static final String IS_ZHOUBAO="isZhouBao";
    public static final String EXTRA_ETERNAL_TITLE = "eternalTitle";
    public static final String EXTRA_WHITE_CONTACT_INFORMATION = "white_contact_information";
    public static final String EXTRA_ADD_WHITE_CONTACT_ID = "add_white_contact_id";
    public static final String EXTRA_DEVICE_ID = "device_id";
    public static final String EXTRA_TRANSFER_BABY="transfer_item";
    public static final String EXTRA_ALARM = "extra_alarm";

    /*app模块*/
    public static final String APP_MAIN = "/app/MainActivity";
    public static final String APP_CONTACT_INFORMATION = "/app/ContactInfoMationActivity";
    public static final String APP_INTERCEPT_STRANGER = "/app/InterceptStrangerControlActivity";
    public static final String APP_DEVICE_MESSAGE = "/app/DeviceMessageActivity";
    public static final String APP_GUARDIAN = "/app/GuardianActivity";
    public static final String APP_SELECT_LOCK = "/app/LockTimeSelectActivity";
    public static final String APP_DISABLE_LIST = "/app/DisableListActivity";
    public static final String APP_MANUAL_BIND = "/bind/ManualBindActivity";
    public static final String APP_BIND = "/bind/BindActivity";
    public static final String EXTRA_IMEI = "extra_imei";
    public static final String EXTRA_CODE = "extra_code";
    public static final String EXTRA_VERIFY = "extra_verify";
    public static final String EXTRA_BIND_TYPE = "extra_bind_type";
    public static final String APP_BINDRELATIONSHOP = "/bind/BindRelationShopActivity";
    public static final String APP_TIMING="/app/TimingActivity";
    public static final String APP_ALARM_CLOCK="/app/AlarmClockActivity";
    //绑定用户信息
    public static final String APP_BABYINFORMATION = "/bind/BabyInformationActivity";
    public static final String EXTRA_BINDID = "extra_uid";
    //定位信息
    public static final String FOOTPRINT_ACTIVITY = "/app/FootprintActivity";
    public static final String LOCATION_ACTIVITY = "/app/LocationActivity";
    public static final String GEOFENCEMAP_ACTIVITY = "/app/GeofenceMapActivity";
    public static final String SUGSEARCH_ACTIVITY = "/app/SugSearchActivity";
    public static final String GEOFENCE_ACTIVITY = "/app/GeofenceActivity";
    public static final String UPLOADSETTING_ACTIVITY = "/app/UploadSettingActivity";
    public static final String APP_BIND_REQUEST = "/app/BindRequestActivity";
    public static final String APP_COMPLAINT_ACTIVITY = "/app/ComplaintActivity";
    public static final String APP_COMPLAINT_OTHER_ACTIVITY = "/app/ComplaintOtherActivity";
    public static final String APP_MIGRATION="/app/MigrationActivity";
    public static final String EXTRA_FENCE_INFO = "fenceInfo";
    public static final String EXTRA_SUG_CITY = "sugCity";
    public static final String EXTRA_SUG_ADDRESS = "sugAddress";
    public static final String EXTRA_COMPLAINT_TYPE = "complaint_type";

    public static final String APP_SCHEDULE="/app/ScheduleActivity";
    public static final String APP_SUBJECT="/app/SubjectActivity";
    public static final String APP_SEARCH_DEVICE="/app/SearchDeviceActivity";
    public static final String APP_WEB_COMMON="/app/CommonWebActivity";
    public static final String APP_CLOCK_TIME_SELECT="/app/AlarmSettingActivity";
    public static final String APP_TRANSFER_LIST="/app/IntegralTransferActivity";
    public static final String APP_TRANSFER_CONTROL="/app/TransferControlActivity";
    public static final String APP_INTEGRAL_RECORD="/app/IntegralRecordAdapter";
    public static final String APP_REPORT_RECORD="/app/ReportRecordActivity";
    /*push模块*/
    //聊天界面
    public static final String APP_CHAT_ACTIVITY = "/push/ChatActivity";
    public static final String EXTRA_UID_IMEI = "to_IMEI";
    //    拨打视频
    public static final String APP_VIDEO_ACTIVITY = "/push/VideoActivity";
    public static final String EXTRA_REMOTEUID = "remoteUid";//对方id
    public static final String EXTRA_REMOTENICK = "remoteNick";//对方昵称，这后面三个只有再拨打时候会带
    public static final String EXTRA_CALL_TYPE = "extra_call_type";//通话方向，接听还是拨打
    public static final String EXTRA_PHONE_TYPE = "extra_phone_type";//通话类型：视频，语音

    //好友界面
    public static final String APP_FRIENDDETAIL_ACTIVITY = "/push/FriendDetailActivity";
    public static final String EXTRA_UID = "to_uid";
    public static final String APP_SEARCHSESSION_ACTIVITY = "/push/SearchSessionActivity";
    public static final String APP_SEARCHFRIEND_ACTIVITY = "/push/SearchFriendActivity";


    //绑定用户的好友关系
    public static final String APP_BINDCHATFRIEND_ACTIVITY = "/push/BindChatFriendActivity";
    public static final String APP_ADD_BINDFRIEND_ACTIVITY = "/push/BinderFriendAddActivity";
    public static final String APP_VERFIY_FRIEND_ACTIVITY = "/push/VerfiyFriendActivity";
    public static final String APP_BINDFRIEND_DETAIL_ACTIVITY = "/push/BinderFriendDetailActivity";
    public static final String EXTRA_FRIEND_INFO = "friendInfo";
    public static final String EXTRA_FRIEND_ID = "friend_id";


    public static final String EXTRA_APPLOCKITEM = "extra_app_lock_item";
    public static final String URL_ALIPAY="h5/Alipay.html";

    //健康守护
    public static final String APP_HEALTH_TEMP = "/app/TempActivity";
    public static final String APP_HEALTH_HEART = "/app/HeartActivity";
    public static final String APP_HEALTH_WALK = "/app/WalkActivity";
    public static final String APP_HEALTH_TEMP_CONTROL = "/app/TempControlActivity";
    public static final String APP_HEALTH_HEART_CONTROL = "/app/HeartControlActivity";
    public static final String APP_HEALTH_TEMP_HELP = "/app/TempHelpActivity";
    public static final String APP_HEALTH_HEART_HELP = "/app/HeartHelpActivity";
    public static final String APP_HEALTH_TEMP_FREQUENCY = "/app/TempFrequnecyActivity";
    public static final String EXTRA_FREQUENCY = "frequency";
    public static final String EXTRA_TEST_FREQUENCY = "test_frequency";
    public static final String APP_HEALTH_WARN = "/app/WarnActivity";
    public static final String EXTRA_WARN_TYPE = "warn_type";
    public static final String APP_HEALTH_TEMP_HISTORY = "/app/TempHistoryActivity";
    public static final String APP_HEALTH_HEART_HISTORY = "/app/HeartHistoryActivity";
    public static final String APP_HEALTH_WALK_TARGET = "/app/WalkTargetActivity";
    public static final String APP_HEALTH_WALK_RANK = "/app/WalkRankActivity";
    public static final String APP_HEALTH_WALK_HISTORY = "/app/WalkHistoryActivity";
    public static final String APP_HEALTH_CODE="/health/HealthCodeActivity";
    public static final String APP_HEALTH_EDIT="/health/HealthEditActivity";
    public static final String EXTRA_CODE_DATA = "extra_code";
}
