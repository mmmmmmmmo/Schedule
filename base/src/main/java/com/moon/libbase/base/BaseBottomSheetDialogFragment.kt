package com.moon.libbase.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.moon.libbase.R
import com.moon.libbase.base.callback.DialogCallback
import com.moon.libbase.base.callback.LoadingCallBack
import com.moon.libbase.base.net.DialogObserver
import com.moon.libbase.base.net.NetStateObserver
import com.moon.libbase.base.net.NetworkState
import com.moon.libbase.utils.ui.screenHeight
import com.moon.libbase.widget.dialog.ProgressDialog
import com.moon.libbase.widget.dialog.ProgressDialogFactory

/**
 * @author ry
 * @date 2019-12-28
 */
abstract class BaseBottomSheetDialogFragment<T : ViewDataBinding> : BottomSheetDialogFragment(),
    DialogCallback {

    abstract val layoutId: Int
    lateinit var dataBinding: T

    //高度大小
    var contentHeight = ViewGroup.LayoutParams.WRAP_CONTENT
    private var dialog: ProgressDialog? = null
    private val helper by lazy {
        BottomBehaviorHelper(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: BottomSheetDialog =
            super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.dismissWithAnimation = true
        dialog.window?.setWindowAnimations(R.style.bottomSheetAnimation)
        return dialog
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

    override fun onStart() {
        super.onStart()
        helper.setContentHeight(contentHeight)
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


    fun setupWithProgress(liveData: LiveData<Boolean>) {
        liveData.observe(this, DialogObserver(this))
    }


    override fun showDialog() {
        if (activity == null) {
            return
        }
        if (dialog == null) {
            dialog = ProgressDialogFactory.Builder(activity).build()
        }
        dialog?.show()
    }

    override fun dismissDialog() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

}