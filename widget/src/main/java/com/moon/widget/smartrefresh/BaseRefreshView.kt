package com.moon.widget.smartrefresh

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.moon.libbase.base.callback.LoadingCallBack
import com.moon.widget.R
import com.moon.widget.stateview.XmStateView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

/**
 * @author ry
 * @date 2020-01-16
 */
@Suppress("LeakingThis")
abstract class BaseRefreshView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    FrameLayout(context, attrs), LoadingCallBack {

    private val refreshLayout: SmartRefreshLayout
    private val stateView: XmStateView

    init {
        //初始化view
        View.inflate(context, R.layout.xm_base_refresh, this)
        refreshLayout = findViewById(R.id.xmRefreshLayout)
        stateView = XmStateView.inject(this)

        //添加内容布局
        refreshLayout.addView(initContentView(),ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
        refreshLayout.setRefreshFooter(ClassicsFooter(getContext()))


        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.BaseRefreshView)
        val loadMore =
            typedArray.getBoolean(R.styleable.BaseRefreshView_rrvEnableLoadMore, false)
        setEnableLoadMore(loadMore)
        val refresh = typedArray.getBoolean(R.styleable.BaseRefreshView_rrvEnableRefresh, false)
        setEnableRefresh(refresh)
        typedArray.recycle()
    }

    abstract fun initContentView(): View

    fun setEnableLoadMore(enabled: Boolean) {
        refreshLayout.setEnableLoadMore(enabled)
    }

    override fun showContent() {
        stateView.showContent()
    }

    override fun showEmpty() {
        stateView.showEmpty()
    }

    override fun showLoading() {
        stateView.showLoading()
    }

    override fun showRetry() {
        stateView.showRetry()
    }

    fun setOnRefreshListener(listener: OnRefreshListener) {
        refreshLayout.setOnRefreshListener(listener)
    }

    override fun onFinish() {
        refreshLayout.finishRefresh()
    }

    fun setEnableRefresh(enabled: Boolean) {
        refreshLayout.setEnableRefresh(enabled)
    }
}