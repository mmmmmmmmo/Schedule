package com.moon.libbase.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

// _ooOoo_  
// o8888888o  
// 88" . "88  
// (| -_- |)  
//  O\ = /O  
// ___/`---'\____  
// .   ' \\| |// `.  
// / \\||| : |||// \  
// / _||||| -:- |||||- \  
// | | \\\ - /// | |  
// | \_| ''\---/'' | |  
// \ .-\__ `-` ___/-. /  
// ___`. .' /--.--\ `. . __  
// ."" '< `.___\_<|>_/___.' >'"".  
// | | : `- \`.;`\ _ /`;.`/ - ` : | |  
// \ \ `-. \_ __\ /__ _/ .-` / /  
// ======`-.____`-.___\_____/___.-`____.-'======  
// `=---='  
//          .............................................  
//           佛曰：bug泛滥，我已瘫痪！

/**
 * TeachAssistant
 * create by NiMa.Wang on 2020/1/13
 */

abstract class BaseBottomVMSheetDialog<T : ViewDataBinding, V : ViewModel> :
    BaseBottomSheetDialogFragment<T>() {
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
    open fun onInject() {}

    override fun initData() {
        super.initData()
        observerData()
    }

    /**
     * 绑定viewModel数据方法
     */
    @CallSuper
    open fun observerData() {
    }
}
