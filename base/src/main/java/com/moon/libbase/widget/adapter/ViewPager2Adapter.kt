package com.moon.libbase.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*

/**
 * @author ry
 * @date 2019-05-23
 */
class ViewPager2Adapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    private val arrayList = ArrayList<PagerItem>()

    fun addFragment(fragment: Fragment, title: String? = null) {
        arrayList.add(PagerItem(fragment, title))
    }

    fun addFragment(pagerItem: PagerItem) {
        arrayList.add(pagerItem)
    }

    fun getTitleList(): List<String> {
        return arrayList.map { it.title ?: "" }
    }

    fun getPagerTitle(position: Int): String? {
        return arrayList[position].title
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun createFragment(position: Int): Fragment {
        return arrayList[position].fragment
    }
}
