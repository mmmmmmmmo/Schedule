package com.zhu.gptproj.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhu.gptproj.R
import com.zhu.gptproj.entity.ProjInCalendar
import org.jetbrains.anko.find

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 03-14-2024 周四 16:10
 */
class ProjInCalendarAdapter :
    ListAdapter<ProjInCalendar, ProjInCalendarAdapter.ProjInCalendarVH>(diff) {

    init {
        val list= arrayListOf<ProjInCalendar>()
        list.add(ProjInCalendar(0,"sdsds","sdsdsdsd"))
        this.submitList(list)
        //this.notifyDataSetChanged()
    }

    //构造器-普通目录
    class ProjInCalendarVH(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup,viewType:Int): ProjInCalendarVH {
                if (viewType==0){
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_proj_in_calendar, parent, false)
                    return ProjInCalendarVH(view)
                }
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_proj_bottom, parent, false)
                return ProjInCalendarVH(view)
            }
        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<ProjInCalendar>() {
            override fun areContentsTheSame(
                oldItem: ProjInCalendar,
                newItem: ProjInCalendar
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ProjInCalendar, newItem: ProjInCalendar): Boolean {
                return oldItem.projId == newItem.projId
            }
        }
    }

    override fun getItemCount(): Int {
        return (super.getItemCount()+1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjInCalendarVH {
        return ProjInCalendarVH.create(parent,viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position==itemCount-1){
            1
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: ProjInCalendarVH, position: Int) {
        if (position<itemCount-1){
            val item = getItem(position)
            holder.itemView
        }

    }
}