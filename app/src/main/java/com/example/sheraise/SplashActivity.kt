package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            goToLoginIntro()
        }, 3000L) // 3-second delay
    }

    private fun goToLoginIntro() {
        val intent = Intent(this, LoginIntroActivity::class.java)
        startActivity(intent)
        finish()
    }
}
