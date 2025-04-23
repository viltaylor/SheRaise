package com.example.sheraise

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginIntroActivity : AppCompatActivity() {

    private lateinit var studentButton: LinearLayout
    private lateinit var mentorButton: LinearLayout
    private lateinit var studentCheck: ImageView
    private lateinit var mentorCheck: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_intro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentButton = findViewById(R.id.studentButton)
        mentorButton = findViewById(R.id.mentorButton)
        studentCheck = findViewById(R.id.studentCheckIcon)
        mentorCheck = findViewById(R.id.mentorCheckIcon)

        studentButton.setOnClickListener {
            selectRole(studentButton, studentCheck)
            deselectRole(mentorButton, mentorCheck)
        }

        mentorButton.setOnClickListener {
            selectRole(mentorButton, mentorCheck)
            deselectRole(studentButton, studentCheck)
        }
    }

    private fun selectRole(button: LinearLayout, checkIcon: ImageView) {
        button.isSelected = true
        checkIcon.visibility = View.VISIBLE
    }

    private fun deselectRole(button: LinearLayout, checkIcon: ImageView) {
        button.isSelected = false
        checkIcon.visibility = View.GONE
    }
}
