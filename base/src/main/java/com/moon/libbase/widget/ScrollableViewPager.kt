package com.moon.libbase.widget

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * @author ry
 * @date 2019-05-25
 * 可配置是否滑动的viewpager
 */
class ScrollableViewPager : ViewPager {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    var isCanScroll = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (!isCanScroll) {
            return false
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (!isCanScroll) {
            return false
        }
        return super.onTouchEvent(ev)
    }
}