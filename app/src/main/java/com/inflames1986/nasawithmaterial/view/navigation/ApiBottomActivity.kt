package com.inflames1986.nasawithmaterial.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.badge.BadgeDrawable
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.databinding.ActivityApiBottomBinding

class ApiBottomActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var transaction: FragmentTransaction
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_bottom_navigation_earth -> {
                    transaction = supportFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(
                        R.animator.slide_in_left,
                        R.animator.slide_in_right
                    )
                    transaction.replace(R.id.container, EarthFragment.newInstance()).commit()
                    true
                }

                R.id.action_bottom_navigation_system -> {
                    transaction = supportFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(
                        R.animator.slide_in_left,
                        R.animator.slide_in_right
                    )
                    transaction.replace(R.id.container, SystemFragment.newInstance()).commit()
                    true
                }

                R.id.action_bottom_navigation_mars -> {
                    transaction = supportFragmentManager.beginTransaction()
                    transaction.setCustomAnimations(
                        R.animator.slide_in_left,
                        R.animator.slide_in_right
                    )
                    transaction.replace(R.id.container, MarsFragment.newInstance()).commit()
                    true
                }
                else -> {
                    true
                }
            }
        }

        binding.bottomNavigation.selectedItemId = R.id.action_bottom_navigation_mars

        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.action_bottom_navigation_mars)
        badge.number = 99999
        badge.maxCharacterCount = 6
        badge.badgeGravity = BadgeDrawable.TOP_END
        binding.bottomNavigation.removeBadge(R.id.action_bottom_navigation_mars)

    }
}