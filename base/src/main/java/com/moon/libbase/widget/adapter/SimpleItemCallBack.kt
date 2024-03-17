package com.moon.libbase.widget.adapter

import androidx.recyclerview.widget.DiffUtil
import java.util.*

/**
 * @author ry
 * @date 2020-02-12
 */
class SimpleItemCallBack<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return true
    }

}