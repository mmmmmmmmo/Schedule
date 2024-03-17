package com.moon.libbase.widget.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ry
 * @date 2020-01-07
 */
abstract class MultiChooseAdapter<T, VH : RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<DataMultiWrap<T>>) :
    ListAdapter<DataMultiWrap<T>, VH>(diffCallback) {
    //每次选择或取消选择的回调
    var checkAllListener: ((Boolean) -> (Unit))? = null
    var selectistener: (() -> (Unit))? = null//点击后监听

    /**
     * 当前是否是全选状态
     * @return true 全选。 false 未全选
     */
    fun checkAllChecked(): Boolean {
        return currentList.find { !it.isChecked } == null
    }

    /**
     * 获取所有当前选择的项
     */
    fun getAllCheckList():ArrayList<T>{
        return currentList.filter { it.isChecked }.map { it.data } as ArrayList<T>
    }

    /**
     * 简单处理，这里可以直接更新当前数据
     * @param position 需要更新的item position值
     */
    fun toggleItem(data: DataMultiWrap<T>, position: Int? = null) {
        data.toggle()
        //每次change了之后去遍历值
        checkAllListener?.invoke(checkAllChecked())
        selectistener?.invoke()
        //如果需要刷新整个item项，传入position
        if (position != null) {
            notifyItemChanged(position)
        }
    }

    /**
     * 设置是否全选
     */
    fun setCheckAll(checkAll: Boolean) {
        currentList.forEach { it.isChecked = checkAll }
        notifyDataSetChanged()
    }
}

data class DataMultiWrap<T>(var data: T, var isChecked: Boolean) {
    fun toggle() {
        isChecked = !isChecked
    }
}