package com.zhu.gptproj.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.moon.libbase.base.BaseActivity
import com.moon.libbase.widget.adapter.PagerItem
import com.moon.libbase.widget.adapter.ViewPagerAdapter
import com.moon.libcommon.base.BaseVMActivity
import com.zhu.gptproj.R
import com.zhu.gptproj.databinding.ActivityMainBinding
import com.zhu.gptproj.ui.fragment.CalendarFragment
import com.zhu.gptproj.ui.fragment.HomeFragment
import com.zhu.gptproj.vm.MainVM

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 05-24-2023 周三 14:16
 */
class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseActivity<ActivityMainBinding>() {
    lateinit var adapter: ViewPagerAdapter


    override fun initView() {
        super.initView()
        adapter = ViewPagerAdapter(supportFragmentManager)
        createFragment()
        dataBinding.bottomNavigation.inflateMenu(R.menu.nav_bottom)
        dataBinding.bottomNavigation.itemIconTintList = null
        dataBinding.viewpager.adapter = adapter
        //dataBinding.viewpager.offscreenPageLimit = 3
    }

    private fun createFragment() {
        adapter.addFragment(PagerItem(HomeFragment(), null))
        adapter.addFragment(PagerItem(CalendarFragment(), null))
    }




    override fun initListener() {
        super.initListener()
        dataBinding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_fragment -> {
                    dataBinding.viewpager.setCurrentItem(0, false)
                }
                R.id.location_fragment ->{
                    dataBinding.viewpager.setCurrentItem(1,false)
                }
            }

            true
        }
    }
}