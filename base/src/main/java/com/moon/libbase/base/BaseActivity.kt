package com.moon.libbase.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import com.moon.libbase.R
import com.moon.libbase.base.callback.DialogCallback
import com.moon.libbase.base.callback.LoadingCallBack
import com.moon.libbase.base.net.DialogObserver
import com.moon.libbase.base.net.NetStateObserver
import com.moon.libbase.base.net.NetworkState
import com.moon.libbase.widget.dialog.ProgressDialog
import com.moon.libbase.widget.dialog.ProgressDialogFactory
import timber.log.Timber

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(), DialogCallback {

    abstract val layoutId: Int
    lateinit var dataBinding: T
    //标记是否需要binding
    open fun hasBinding() = true

    var dialog: ProgressDialog? = null


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        //这里打印类名用于，快速索引Activity类名
        Timber.d("onCreate:${this::class.java.simpleName}" )
        beforeOnCreate()
        super.onCreate(savedInstanceState)
        if (hasBinding()) {
            dataBinding = DataBindingUtil.setContentView(this, layoutId)
            dataBinding.lifecycleOwner = this
        } else {
            setContentView(layoutId)
        }
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
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        toolbar?.let {
            setSupportActionBar(it)
            setupToolbar(it)
        }
    }
    fun setToolbarTitle(title:String?){
        val toolbar: Toolbar? = findViewById(R.id.toolbar)
        toolbar?.let {
            it.title = title
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

    fun onOptionsItemSelectedRetive(item:MenuItem?):Boolean{
        if(item!=null){
            return super.onOptionsItemSelected(item)
        }else{
            return true
        }

    }

}

