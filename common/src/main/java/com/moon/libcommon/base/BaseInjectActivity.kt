package com.moon.libcommon.base


import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.moon.libbase.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author ry
 * @date 2019-05-15
 * 默认注入activity，用于fragment的注入
 */
abstract class BaseInjectActivity<T : ViewDataBinding> : BaseCommonActivity<T>(), HasFragmentInjector, HasSupportFragmentInjector {
    @Inject
    @JvmField
    var supportFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    @Inject
    @JvmField
    var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        onInject()
        super.onCreate(savedInstanceState)
    }

    //注入之后初始化其他值
    open fun onInject(){}

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? = frameworkFragmentInjector
}