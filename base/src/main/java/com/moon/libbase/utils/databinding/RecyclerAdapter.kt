package com.moon.libbase.utils.databinding

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.moon.libbase.utils.extension.setDivider

/**
 * @author ry
 * @date 2019-12-25
 */


@BindingAdapter(value = ["dividerOrientation", "dividerDrawable"], requireAll = false)
fun setRecyclerDivider(view: RecyclerView, orientation: Int?, drawable: Drawable?) {
   view.setDivider(orientation,drawable)
}