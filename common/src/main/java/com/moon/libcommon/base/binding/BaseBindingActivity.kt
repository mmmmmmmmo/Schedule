package com.moon.libcommon.base.binding

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.moon.libbase.base.ActivityManager
import com.moon.libbase.base.callback.DialogCallback
import com.moon.libbase.base.callback.LoadingCallBack
import com.moon.libbase.base.net.DialogObserver
import com.moon.libbase.base.net.NetStateObserver
import com.moon.libbase.base.net.NetworkState
import com.moon.libbase.widget.dialog.ProgressDialog
import com.moon.libbase.widget.dialog.ProgressDialogFactory
import com.moon.libcommon.R
import com.moon.libcommon.databinding.ActivityBaseHeaderBinding

/**
 * @author ry
 * @date 2020-01-07
 * 封装toolbar
 */
abstract class BaseBindingActivity<T : ViewDataBinding> : AppCompatActivity(), DialogCallback {

    abstract val layoutId: Int
    lateinit var dataBinding: T

    var dialog: ProgressDialog? = null
    private var isLoadData = true
    lateinit var baseBinding: ActivityBaseHeaderBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        beforeOnCreate()
        super.onCreate(savedInstanceState)

        baseBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.activity_base_header,
                null,
                false
            )
        dataBinding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)

        val params = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        baseBinding.container.addView(dataBinding.root, 0, params)

        setContentView(baseBinding.root)

        ActivityManager.getInstance().addActivity(this)
        initStatusBar()
        initToolBar()
        initView()
        initData()
        initListener()
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

    override fun onDestroy() {
        dismissDialog()
        ActivityManager.getInstance().removeActivity(this)
        super.onDestroy()
    }

    override fun showDialog() {
        if (dialog == null) {
            dialog = ProgressDialogFactory.Builder(this).build()
        }
        dialog?.show()
    }

    override fun dismissDialog() {
        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }


    private fun initToolBar() {
        val toolbar: Toolbar? = findViewById(com.moon.libbase.R.id.toolbar)
        toolbar?.let {
            setSupportActionBar(it)
            setupToolbar(it)
        }
    }


    /**
     * onCreate方法执行之前，用于一些初始化方法
     */
    open fun beforeOnCreate() {}

    /**
     * 状态栏操作
     */
    open fun initStatusBar() {}

    /**
     * 对toolbar的操作方法
     */
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}