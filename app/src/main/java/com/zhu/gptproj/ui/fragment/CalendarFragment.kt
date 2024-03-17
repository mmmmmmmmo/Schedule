package com.zhu.gptproj.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.moon.libbase.base.BaseFragment
import com.zhu.gptproj.R
import com.zhu.gptproj.adapter.ProjInCalendarAdapter
import com.zhu.gptproj.databinding.FragmentCalendarBinding
import com.zhu.gptproj.entity.ProjInCalendar


/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 03-14-2024 周四 10:36
 */
class CalendarFragment : BaseFragment<FragmentCalendarBinding>(){

    override val layoutId: Int= R.layout.fragment_calendar
    var projAdapter:ProjInCalendarAdapter?=null



    override fun initView() {
        super.initView()
        projAdapter= ProjInCalendarAdapter()
        dataBinding.calendarRecycler.adapter =projAdapter.also {
            if (it==null){
                projAdapter=ProjInCalendarAdapter()
            }
        }

    }
}