package com.moon.libcommon.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moon.libbase.base.BaseDialogFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * @author ry
 * @date 2020/3/9
 */
abstract class BaseVMDialogFragment<T : ViewDataBinding,V : ViewModel> : BaseDialogFragment<T>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: V

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onInject()
        super.onViewCreated(view, savedInstanceState)
    }

    //注入之后初始化其他值
    open fun onInject(){}

    override fun initData() {
        super.initData()
        observerData()
    }

    /**
     * 绑定viewModel数据方法
     */
    @CallSuper
    open fun observerData(){}
}