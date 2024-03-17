package com.moon.libbase.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

/**
 * @author ry
 * @date 2019-05-23
 */
class ViewPagerAdapter(fm: FragmentManager) : NoCacheFragmentPagerAdapter(fm) {

    private val arrayList = ArrayList<PagerItem>()
    override fun getItem(position: Int): Fragment {
        return arrayList[position].fragment
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    fun addFragment(pageItem: PagerItem) {
        arrayList.add(pageItem)
    }

    fun addFragment(index: Int, pageItem: PagerItem) {
        arrayList.add(index, pageItem)
    }

    fun addFragment(fragment: Fragment, pageTitle: String? = null) {
        arrayList.add(PagerItem(fragment, pageTitle))
    }

    fun clearFragment() {
        arrayList.clear()
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrayList[position].title
    }

    fun setPageTitle(position: Int, title: String) {
        arrayList.getOrNull(position)?.let {
            it.title = title
            notifyDataSetChanged()
        }
    }
}

data class PagerItem(var fragment: Fragment, var title: String? = null)