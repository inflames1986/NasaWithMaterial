package com.inflames1986.nasawithmaterial.view.navigation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.inflames1986.nasawithmaterial.view.navigation.EarthFragment
import com.inflames1986.nasawithmaterial.view.navigation.MarsFragment
import com.inflames1986.nasawithmaterial.view.navigation.SystemFragment

class ViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragments.size

    private val fragments = arrayOf(EarthFragment(),  SystemFragment(), MarsFragment())
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}