package com.moon.libbase.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.moon.libbase.R
import com.moon.libbase.base.callback.DialogCallback
import com.moon.libbase.base.callback.LoadingCallBack
import com.moon.libbase.base.net.DialogObserver
import com.moon.libbase.base.net.NetStateObserver
import com.moon.libbase.base.net.NetworkState
import com.moon.libbase.widget.dialog.ProgressDialog
import com.moon.libbase.widget.dialog.ProgressDialogFactory
import io.reactivex.disposables.CompositeDisposable

/**
 * @author ry
 * @date 2019-05-13
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment(), DialogCallback {

    abstract val layoutId: Int
    lateinit var dataBinding: T

    //标记是否需要binding
    open fun hasBinding() = true

     var dialog: ProgressDialog? = null
    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (hasBinding()) {
            dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            dataBinding.lifecycleOwner = this
            dataBinding.root
        } else {
            inflater.inflate(layoutId, container, false)
        }
    }

    override fun onDestroyView() {
        dismissDialog()
        super.onDestroyView()
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


    fun navigationTo(@IdRes resId: Int, @Nullable args: Bundle? = null) {
        val control = NavHostFragment.findNavController(this)
        val options = NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_int_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()
        control.navigate(resId, args, options)
    }

    fun setupWithProgress(liveData: LiveData<Boolean>) {
        liveData.observe(this, DialogObserver(this))
    }

    fun setupWithRefreshRecycler(
        state: LiveData<NetworkState>,
        refreshLayout: LoadingCallBack
    ) {
        state.observe(this, NetStateObserver(refreshLayout))
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