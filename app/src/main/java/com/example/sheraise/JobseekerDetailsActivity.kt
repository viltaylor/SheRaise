package com.example.sheraise

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JobseekerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobseeker_detail)

        val backButton: ImageButton = findViewById(R.id.btnBack)
        backButton.setOnClickListener {
            finish()
        }

        val applyButton: Button = findViewById(R.id.btnApply)
        applyButton.setOnClickListener {
            Toast.makeText(this, "Applied successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}
