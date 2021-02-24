package com.example.yummy.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yummy.R
import com.example.yummy.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        buttonSplash.setOnClickListener {
            startActivity(MainActivity.getIntent(this))
            overridePendingTransition(R.anim.splash_in, R.anim.splash_out)
            finish()
        }
    }
}
