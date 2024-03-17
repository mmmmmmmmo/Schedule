package com.moon.libcommon.db

//消息推送的类型
enum class PushType(val value: Int) {

    HEARTS_WEB(2),//服务端下发的心跳
    HEARTS(3),//设备心跳
    CHAT_MESSAGE(80),//聊天消息
    VIDEO_MESSAGE(84),//视频聊天消息，激活指令
    BIND_SET_ADMIN(1001),//被提升为管理员
    BIND_DELETE_ADMIN(1002),//被移除管理员
    BIND_DELETE(1001),//移除绑定关系

    //手机
    LOGIN(301),

    /**
     * 绑定申请，已这个type开始同意处理，因为部分type已经不适合本项目，只取其中几个同意处理
     */
    AppBindRequest(302),
    AppSendPic(303),

    /**
     * 手表解绑
     */
    AppBindRemove(304),

    /**
     * 变更手表管理员
     */
    AppUpdateManage(305),

    /**
     * 绑定刷新，包括绑定成功和解除绑定
     */
    AppBindApp(306),

    /**
     * 异地登录通知
     */
    RemoteLogin(307),

    /**
     * 低电量
     */
    AppOrderElectric(308),

    /**
     * 设备关机
     */
    AppOrderPoweroff(309),

    /**
     * 设备重启
     *
     */
    AppOrderReboot(310),

    /**
     * SOS报警提醒
     */
    AppSos(311),

    /**
     * 监听
     */
    AppMonitorDevice(312),

    /**
     * 话费查询相应
     */
    AppCostResponse(313),

    /**
     * 没有免费视频时间
     */
    AppVideoTimeTooLong(314),

    /**
     * 手表手机号码变更
     */
    AppPhoneRefresh(315),

    /**
     * gps请求
     */
    AppGpsRequest(316),

    /**
     * GPS时间间隔和上传时间有变化，定位频率修改
     */
    AppGpsUptimeRate(317),

    /**
     * 进出围栏警报
     */
    AppWatchFenceWarn(318),

    /**
     * 更新电子围栏
     */
    AppWatchUpdateFence(319),

    /**
     * 手机控制交友，发送请求
     */
    AppWatchSendFriendRequest(320),

    /**
     * 管理员同意绑定申请
     */
    AppWatchUpdateAPPFriend(321),

    AppNewMessage(322),

    /**
     * 收到管理员转让信息
     */
    ADMINGIVED(323),

    /**
     * 手表上下线提醒
     */
    WatchOnLineChange(324),

    /**
     * 收到体温测量结果
     */
    AppHealthTempResult(334),

    /**
     * 收到心率测量结果
     */
    AppHealthHeartResult(335),

    /**
     * 收到心率报警
     */
    AppHealthHeartWarn(338),

    /**
     * 收到体温报警
     */
    AppHealthTempWarn(339),

    /**
     * 占位符，标识小与350的走这个处理
     */
    App300END(350);
}

enum class MessageType(val value: Int) {
    TEXT(1),
    IMAGE(2),
    AUDIO(3);

    companion object {
        fun valueOf(value: Int): MessageType {
            return when (value) {
                1 -> TEXT
                2 -> IMAGE
                3 -> AUDIO
                else -> TEXT
            }
        }
    }
}