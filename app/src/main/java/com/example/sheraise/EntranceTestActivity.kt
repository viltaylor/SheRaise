package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EntranceTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_entrance_test)

        // Safely find the root view (must match an ID in the layout XML)
        val rootView = findViewById<android.view.View>(R.id.root)
        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val loginLink = findViewById<TextView>(R.id.loginLink)
        loginLink?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        // Start Learning button
        val btnStartLearning = findViewById<Button>(R.id.getStartedButton)
        btnStartLearning?.setOnClickListener {
            val intent = Intent(this, LearningSelectionActivity::class.java)
            startActivity(intent)
        }
    }
}
