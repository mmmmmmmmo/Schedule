package com.moon.libbase.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import com.moon.libbase.R
import com.moon.libbase.base.callback.LoadingCallBack
import com.moon.libbase.base.net.NetStateObserver
import com.moon.libbase.base.net.NetworkState

/**
 * @author ry
 * @date 2020/3/9
 */
abstract class BaseDialogFragment<T : ViewDataBinding> : DialogFragment() {

    abstract val layoutId: Int
    lateinit var dataBinding: T


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val onCreateDialog = super.onCreateDialog(savedInstanceState)
        onCreateDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return onCreateDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolBar(view)
        initView()
        initData()
        initListener()
    }


    open fun initToolBar(view: View) {
        val toolbar: Toolbar? = view.findViewById(R.id.toolbar)
        toolbar?.let {
            setupToolbar(it)
        }
    }

    open fun setupToolbar(toolbar: Toolbar) {}
    @CallSuper
    open fun initView() {
    }

    @CallSuper
    open fun initData() {
    }

    @CallSuper
    open fun initListener() {
    }

    fun setupWithRefreshRecycler(
        state: LiveData<NetworkState>,
        refreshLayout: LoadingCallBack
    ) {
        state.observe(this, NetStateObserver(refreshLayout))
    }
}