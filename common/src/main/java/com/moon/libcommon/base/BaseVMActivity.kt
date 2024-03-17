package com.moon.libcommon.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * @author ry
 * @date 2019-05-24
 * 用于viewmodel的注入
 */
abstract class BaseVMActivity<T : ViewDataBinding, V : ViewModel> : BaseInjectActivity<T>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: V

    inline fun <reified T : ViewModel> injectModel(): T {
        return ViewModelProvider(this, viewModelFactory).get(T::class.java)
    }

    override fun initData() {
        super.initData()
        observerData()
    }

    /**
     * 绑定viewModel数据方法
     */
    open fun observerData() {}
}