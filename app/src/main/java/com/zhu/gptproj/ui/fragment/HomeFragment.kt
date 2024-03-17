package com.zhu.gptproj.ui.fragment

import android.app.ActionBar.LayoutParams
import android.widget.RelativeLayout
import android.widget.TextView
import com.moon.libbase.base.BaseFragment
import com.zhu.gptproj.R

import com.zhu.gptproj.databinding.FragmentHomeBinding
import com.zhu.gptproj.widget.DragTV

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 05-24-2023 周三 15:17
 */
class HomeFragment (override val layoutId: Int = R.layout.fragment_home): BaseFragment<FragmentHomeBinding>(){

    override fun initListener() {
        super.initListener()
        dataBinding.btnConfirm.setOnClickListener{
            val params= RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
            params.topMargin=100
            params.leftMargin=100
            dataBinding.thingPool.addView(object :DragTV(context,0,dataBinding.etInput.text.toString()){},params)
            dataBinding.etInput.text=null
        }
    }
}