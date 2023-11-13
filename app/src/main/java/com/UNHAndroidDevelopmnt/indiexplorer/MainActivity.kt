package com.UNHAndroidDevelopmnt.indiexplorer

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //my changes for code
        //setting notch or status to transparent
        window.statusBarColor=Color.TRANSPARENT

        //creating a handler
        //handler to change screen to signup page in 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this,SignUpActivity::class.java))
                    finish()
                },3000)

    }
}