package com.zhu.gptproj.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhu.gptproj.R


/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 03-25-2024 周一 13:20
 */
class BoxAdapter : ListAdapter<String, BoxAdapter.BoxViewHolder>(diff) {


    //构造器-普通目录
    class BoxViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val boxName=view.findViewById<TextView>(R.id.box_name)

        companion object {
            fun create(parent: ViewGroup, viewType:Int): BoxViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_box, parent, false)
                return BoxViewHolder(view)
            }
        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<String>() {
            override fun areContentsTheSame(
                oldItem: String,
                newItem: String
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        return BoxViewHolder.create(parent,viewType)
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        val item = getItem(position)
        holder.boxName.text=item
    }

}