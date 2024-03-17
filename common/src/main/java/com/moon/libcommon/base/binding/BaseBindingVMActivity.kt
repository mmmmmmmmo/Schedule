package com.moon.libcommon.base.binding

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author ry
 * @date 2020-01-08
 */
abstract class BaseBindingVMActivity<T : ViewDataBinding, V : ViewModel> : BaseBindingActivity<T>(),
    HasFragmentInjector, HasSupportFragmentInjector {
    @Inject
    @JvmField
    var supportFragmentInjector: DispatchingAndroidInjector<Fragment>? = null

    @Inject
    @JvmField
    var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        onInject()
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        super.initData()
        observerData()
    }

    /**
     * 绑定viewModel数据方法
     */
    open fun observerData() {}

    //注入之后初始化其他值
    open fun onInject() {}

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? =
        frameworkFragmentInjector
}