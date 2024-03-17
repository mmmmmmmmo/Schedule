package com.moon.libbase.utils.extension

import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.moon.libbase.R
import com.moon.libbase.widget.adapter.ViewPager2Adapter

/**
 * @author ry
 * @date 2019-12-12
 */
fun View.click(listen: () -> Unit) {
    setOnClickListener {
        listen.invoke()
    }
}

fun View.setMargin(left: Int? = null, right: Int? = null, top: Int? = null, bottom: Int? = null) {
    val layoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    left?.let { layoutParams.leftMargin = it }
    right?.let { layoutParams.rightMargin = it }
    top?.let { layoutParams.topMargin = it }
    bottom?.let { layoutParams.bottomMargin = it }
    setLayoutParams(layoutParams)
}


fun EditText.showPassword(isShow: Boolean) {
    if (isShow) {
        this.transformationMethod = HideReturnsTransformationMethod.getInstance()
    } else {
        this.transformationMethod = PasswordTransformationMethod.getInstance()
    }
    this.setSelection(this.text.length)
}

fun TabLayout.setupWithViewPager2(viewPager: ViewPager2, labels: List<String>): TabLayoutMediator {

    if (labels.size != viewPager.adapter?.itemCount)
        throw Exception("The size of list and the tab count should be equal!")

    val mediator = TabLayoutMediator(this, viewPager,
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = labels[position]
        })
    mediator.attach()
    return mediator
}

fun TabLayout.setupWithViewPager2(
    viewPager: ViewPager2,
    adapter: ViewPager2Adapter
): TabLayoutMediator {
    val mediator = TabLayoutMediator(this, viewPager,
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = adapter.getPagerTitle(position)
        })
    mediator.attach()
    return mediator
}

fun RecyclerView.setDivider(orientation: Int? = null, drawable: Drawable? = null) {
    val itemDecoration =
        DividerItemDecoration(context, orientation ?: DividerItemDecoration.VERTICAL)
    if (drawable != null) {
        itemDecoration.setDrawable(drawable)
    } else {
        val shape = ShapeDrawable(RectShape())
        shape.paint.apply {
            color = ContextCompat.getColor(context, R.color.xm_divider)
            style = Paint.Style.FILL
        }
        shape.intrinsicHeight = context.resources.getDimensionPixelOffset(R.dimen.xm_divider_height)
        itemDecoration.setDrawable(shape)
    }
    addItemDecoration(itemDecoration)
}