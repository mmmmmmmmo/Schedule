package com.moon.libbase.widget.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ry
 * @date 2020-01-07
 */
abstract class SingleChooseAdapter<T, VH : RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {
    protected val helper: SingleChooseHelper by lazy { SingleChooseHelper(this) }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBindChooseViewHolder(holder, position, helper.isChoose(position))
    }

    abstract fun onBindChooseViewHolder(holder: VH, position: Int, isChoose: Boolean)

    /**
     * 返回选择项的position值，没有选择返回-1
     */
    fun getChoosePosition(): Int {
        return helper.lastChoose
    }

    fun getChooseItem(): T? {
        return currentList.getOrNull(getChoosePosition())
    }

    override fun submitList(list: List<T>?) {
        helper.initChoose()
        super.submitList(list)
    }

    fun submitListWithChoose(list: List<T>?, chooseIndex: Int = -1) {
        helper.lastChoose = chooseIndex
        super.submitList(list)
    }

    fun changeChoose(position: Int) {
        helper.changeChoose(position)
    }

    override fun submitList(list: List<T>?, commitCallback: Runnable?) {
        helper.initChoose()
        super.submitList(list, commitCallback)
    }
}