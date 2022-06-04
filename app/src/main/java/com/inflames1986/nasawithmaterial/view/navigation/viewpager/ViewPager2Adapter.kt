package com.inflames1986.nasawithmaterial.view.navigation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.inflames1986.nasawithmaterial.view.navigation.EarthFragment
import com.inflames1986.nasawithmaterial.view.navigation.MarsFragment
import com.inflames1986.nasawithmaterial.view.navigation.SystemFragment
import com.inflames1986.nasawithmaterial.view.picture.PictureOfTheEarthFragment
import com.inflames1986.nasawithmaterial.view.picture.PictureOfTheMarsFragment

class ViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragments.size

    private val fragments = arrayOf(PictureOfTheEarthFragment(), PictureOfTheMarsFragment(),  SystemFragment() )
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}