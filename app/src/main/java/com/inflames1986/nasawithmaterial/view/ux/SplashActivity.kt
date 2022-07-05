package com.inflames1986.nasawithmaterial.view.ux

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.view.MainActivity

@SuppressLint("CustomSplashScreen") // Android 12
class
SplashActivity : AppCompatActivity() { //TODO HW single-activity пытаемся сохранить
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setTheme(R.style.MyBlueTheme)
        setContentView(R.layout.activity_splash)

        /*ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.imageView), View.ROTATION, 720f)
            .setDuration(4000).start()*/
        findViewById<ImageView>(R.id.imageView).animate().rotationBy(720f).setDuration(4000).start()
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }
}