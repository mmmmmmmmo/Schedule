package com.moon.libbase.widget.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * @author ry
 * @date 2020-01-06
 */
class SingleChooseHelper(val adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>) {
    internal var lastChoose: Int = -1

    fun isChoose(position: Int): Boolean {
        return lastChoose == position
    }

    fun initChoose(){
        lastChoose = -1
    }

    fun changeChoose(position: Int) {
        if (position == lastChoose) return
        val prePosition = lastChoose
        if (prePosition != -1) {
            //更新原视图
            adapter.notifyItemChanged(prePosition)
        }
        lastChoose = position
        //直接更新现视图会刷新整个item，导致item闪烁，
        // 一个更好的方法是手动去调用部分刷图的刷新代码
        //更新现视图
        adapter.notifyItemChanged(lastChoose)
    }
}