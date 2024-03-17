package com.moon.libcommon.utils.rxbus

import com.moon.libcommon.entity.HomeHealthData
import com.moon.libcommon.entity.vo.HealthTimeSetting
import com.moon.libcommon.entity.vo.HomeDeviceInfo
import com.moon.libcommon.entity.vo.UnitSetting


/**
 * @author ry
 * @date 2019-07-31
 */

//改变模式或者改变imei时，需要重启activity
class ChangeModeOrImei

class BindDeviceSuccess
class BindListRefresh

class VideoActivated

class VideoDisable

class UnBindDeviceSuccess

class RelaunchMain

class UpdateSuccess

class UpdateFence

class LogOut

class StopService

class ExitLoginActivity

class NewBindRequest(var value: Int)

class HaveNewMessage(var value: Int)

class NewChatMessage(var imei: String)

class ToTargetRecord

class ToVideoActivity

class NoticeRegisterId

class NewWatchFenceWarn

class NewWatchFriendRequest

class WatchOnLineChange(var watch_id: String,var state: Int)

class NewTempTest(var data: String)

class NewHeartTest(var data: String)

class NewHeartWarn(var value: Int)

class NewTempWarn(var value: Int)
