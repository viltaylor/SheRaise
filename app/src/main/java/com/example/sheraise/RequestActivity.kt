package com.example.sheraise

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class RequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        val backButton = findViewById<ImageButton>(R.id.btnBack)
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}