package com.example.yummy.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.yummy.R
import com.example.yummy.ui.main.MainActivity

private const val TIME: Long = 2000

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(MainActivity.getIntent(this))
            overridePendingTransition(R.anim.splash_in, R.anim.splash_out)
            finish()
        }, TIME)
    }
}
