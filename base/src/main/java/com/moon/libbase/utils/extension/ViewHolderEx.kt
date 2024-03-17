package com.moon.libbase.utils.extension

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ry
 * @date 2019-12-31
 */

//ViewHolder 获取字符串
fun RecyclerView.ViewHolder.getString(@StringRes resId: Int, vararg formatArgs: Any): String {
    return if (!formatArgs.isNullOrEmpty()) {
        itemView.context.getString(resId, *formatArgs)
    } else {
        itemView.context.getString(resId)
    }
}
//ViewHolder 获取颜色
fun RecyclerView.ViewHolder.getColor(@ColorRes resId: Int):Int{
    return ContextCompat.getColor(itemView.context,resId)
}