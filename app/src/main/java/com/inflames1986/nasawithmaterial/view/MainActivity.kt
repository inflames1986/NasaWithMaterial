package com.inflames1986.nasawithmaterial.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.view.picture.PictureOfTheDayFragment

const val RedTheme = 1
const val BlueTheme = 2

class MainActivity : AppCompatActivity() {

    private val KEY_SP = "sp"
    private val KEY_CURRENT_THEME = "current_theme"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyGreenTheme)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.container,
                PictureOfTheDayFragment.newInstance()
            ).commit()
        }
    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
    }

    fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, -1)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            RedTheme -> R.style.MyRedTheme
            BlueTheme -> R.style.MyBlueTheme
            else -> 0
        }
    }
}