package com.moon.libcommon.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moon.libbase.utils.databinding.loadUrl
import com.moon.libcommon.R
import org.jetbrains.anko.find

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 02-06-2023 周一 14:27
 */
class CourseTypeAdapter() :androidx.recyclerview.widget.ListAdapter<String, CourseTypeAdapter.CourseTypeVH>(diff){
    var clickListener: ((String) -> Unit)? = null

    var mContext: Context?=null

    constructor(context: Context):this(){
        mContext=context
    }
    override fun onBindViewHolder(holder: CourseTypeVH, position: Int) {
        val item=getItem(position)
        holder.courseName.setText(item)
        holder.courseName.setOnClickListener {
            clickListener?.invoke(item)

        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<String>() {
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    class CourseTypeVH(view: View) : RecyclerView.ViewHolder(view) {

        val courseName = view.find<TextView>(R.id.course_name)

        companion object {
            fun create(parent: ViewGroup): CourseTypeVH {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course_type,parent,false)
                return CourseTypeVH(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseTypeVH {
        return CourseTypeVH.create(parent)
    }
}