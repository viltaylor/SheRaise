package com.example.sheraise

import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JobSeekerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_seeker)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // Optional: Add listener for "See all"
        findViewById<TextView>(R.id.tvSeeAll).setOnClickListener {
            Toast.makeText(this, "See all jobs clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}
