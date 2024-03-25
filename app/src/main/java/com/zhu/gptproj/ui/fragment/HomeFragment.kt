package com.zhu.gptproj.ui.fragment

import android.app.ActionBar.LayoutParams
import android.widget.GridLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.moon.libbase.base.BaseFragment
import com.zhu.gptproj.R
import com.zhu.gptproj.adapter.BoxAdapter

import com.zhu.gptproj.databinding.FragmentHomeBinding
import com.zhu.gptproj.widget.DragTV

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 05-24-2023 周三 15:17
 */
class HomeFragment (override val layoutId: Int = R.layout.fragment_home): BaseFragment<FragmentHomeBinding>(){

    private val boxAdapter: BoxAdapter by lazy{
        return@lazy BoxAdapter()
    }
    override fun initView() {
        super.initView()
        val spanCount = 3
        dataBinding.boxList.layoutManager = GridLayoutManager(context,spanCount)
        dataBinding.boxList.adapter=boxAdapter
    }

    override fun initListener() {
        super.initListener()
        dataBinding.btnConfirm.setOnClickListener{
            val params= RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
            params.topMargin=100
            params.leftMargin=100

            val newDragView=DragTV(context,0,dataBinding.etInput.text.toString())

            dataBinding.thingPool.addView(newDragView,params)
            dataBinding.etInput.text=null
        }
    }

    override fun initData() {
        super.initData()
        val boxList = resources.getStringArray(R.array.home_box_list).toMutableList()
        boxAdapter.submitList(boxList)
    }
}