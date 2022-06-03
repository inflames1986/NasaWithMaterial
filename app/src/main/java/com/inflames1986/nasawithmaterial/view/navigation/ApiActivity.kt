package com.inflames1986.nasawithmaterial.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.databinding.ActivityApiBinding
import com.inflames1986.nasawithmaterial.view.navigation.viewpager.ViewPager2Adapter


class ApiActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPager2Adapter(this)


        TabLayoutMediator(binding.tabLayout,binding.viewPager,object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                //TDDO HW настраиваем tab
                tab.text = when(position){
                    0-> R.string.earth.toString()
                    1-> R.string.mars.toString()
                    else -> R.string.system.toString()
                }
            }
        } ).attach()

       setCustomTabs()
    }

    private fun setCustomTabs() {
        binding.tabLayout.getTabAt(0)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tabitem_earth, null)
        binding.tabLayout.getTabAt(1)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tabitem_mars, null)
        binding.tabLayout.getTabAt(2)?.customView =
            layoutInflater.inflate(R.layout.activity_api_tabitem_system, null)
    }
}