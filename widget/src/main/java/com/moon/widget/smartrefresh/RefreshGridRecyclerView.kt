package com.moon.widget.smartrefresh

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moon.libbase.utils.extension.setDivider

/**
 * @author ry
 * @date 2019-12-23
 *
 * 封装smartRefreshLayout和StateRecyclerView
 */
class RefreshGridRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : BaseRefreshView(context, attrs) {

    lateinit var recyclerView: RecyclerView
    override fun initContentView(): View {
        recyclerView = RecyclerView(context).apply {
            layoutManager = GridLayoutManager(context,4)
        }
        return recyclerView
    }

    fun setAdapter(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>) {
        recyclerView.adapter = adapter
    }

}