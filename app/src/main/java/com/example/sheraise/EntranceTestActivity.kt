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

        // Handle insets for status/navigation bars
        findViewById<android.view.View>(R.id.root)?.let { rootView ->
            ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Link to login screen
        findViewById<TextView>(R.id.loginLink)?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Start learning - navigate to learning selection screen
        findViewById<Button>(R.id.getStartedButton)?.setOnClickListener {
            startActivity(Intent(this, LearningSelectionActivity::class.java))
        }
    }
}
