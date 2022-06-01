package com.inflames1986.nasawithmaterial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.view.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.MyBlueTheme)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container,
                PictureOfTheDayFragment.newInstance()).commit()
        }
    }
}