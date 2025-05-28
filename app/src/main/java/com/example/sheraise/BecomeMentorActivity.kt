package com.sheraise.ui.mentor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sheraise.R

class BecomeMentorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_mentor)

        val getStartedBtn = findViewById<Button>(R.id.btnGetStarted)
        getStartedBtn.setOnClickListener {
            // Navigate to the form page
            val intent = Intent(this, MentorFormActivity::class.java)
            startActivity(intent)
        }
    }
}
