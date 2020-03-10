package com.example.urbandicionary.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }
}
