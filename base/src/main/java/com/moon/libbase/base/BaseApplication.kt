package com.moon.libbase.base

import android.app.Activity
import android.app.Service
import android.os.Handler
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.moon.libbase.BuildConfig
import com.moon.libbase.dl.AppInjector
import com.moon.libbase.utils.MoonDebugTree
import com.moon.libbase.utils.ui.ToastUtils
import com.tencent.mmkv.MMKV
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates


/**
 * @author ry
 * @date 2019-05-11
 */
abstract class BaseApplication : MultiDexApplication(), HasActivityInjector, HasServiceInjector {

    companion object {
        var instance: BaseApplication by Delegates.notNull()
    }

    private var appCount = 0

    fun isOnScreen(): Boolean {
        return appCount > 0
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
        instance = this
        initApplication()
        registerActivityLifecycleCallbacks(object : EmptyActivityLifecycleCallbacks() {
            override fun onActivityStarted(activity: Activity) {
                super.onActivityStarted(activity)
                appCount++
            }

            override fun onActivityStopped(activity: Activity) {
                super.onActivityStopped(activity)
                appCount--
            }
        })
    }

    private fun initApplication() {
        //init doraemon
//        DoraemonKit.install(this)

        if (BuildConfig.DEBUG) {
            // default logging
            Timber.plant(MoonDebugTree())
        }
        //ARouter

        if (BuildConfig.DEBUG) {
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化

        AppInjector.init(this)
        //公共toast
        ToastUtils.init(this)

        Handler().postDelayed(Runnable {
            postInit()
        }, 5000)

        //MMKV
        MMKV.initialize(this)
        //UMConfigure.init(this, "5faa28011c520d3073a4d2fe", BuildConfig.DEFAULT_DEVICE, UMConfigure.DEVICE_TYPE_PHONE, "")
        //UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        //MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
    }

    private fun postInit() {
        //init bugly
        //CrashReport.initCrashReport(applicationContext, BuildConfig.BUGLY_ID, BuildConfig.DEBUG)
    }

    fun isAppForeground(): Boolean {
        return appCount > 0
    }


    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
    override fun serviceInjector(): AndroidInjector<Service> = dispatchingServiceInjector
}