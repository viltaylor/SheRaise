package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.animation.AnimationUtils
import android.widget.Button

class LoginIntroActivity : AppCompatActivity() {

    private lateinit var studentButton: FrameLayout
    private lateinit var mentorButton: FrameLayout
    private lateinit var studentCheck: ImageView
    private lateinit var mentorCheck: ImageView
    private lateinit var confirmButton: Button
    private var selectedRole: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_intro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentButton = findViewById<FrameLayout>(R.id.studentButton)
        mentorButton = findViewById<FrameLayout>(R.id.mentorButton)
        studentCheck = findViewById(R.id.studentCheckIcon)
        mentorCheck = findViewById(R.id.mentorCheckIcon)
        confirmButton = findViewById(R.id.confirmButton)

        confirmButton.setOnClickListener {
            when (selectedRole) {
                "student" -> {
                    // TODO: Start Student activity
                    startActivity(Intent(this, EntranceTestActivity::class.java))
                }
                "mentor" -> {
//                    // TODO: Start Mentor activity
//                    startActivity(Intent(this, MentorActivity::class.java))
                }
            }
        }


        studentButton.setOnClickListener {
            selectRole(studentButton, studentCheck)
            deselectRole(mentorButton, mentorCheck)
            selectedRole = "student"
            enableConfirmButton()
        }

        mentorButton.setOnClickListener {
            selectRole(mentorButton, mentorCheck)
            deselectRole(studentButton, studentCheck)
            selectedRole = "mentor"
            enableConfirmButton()
        }

    }

    private fun selectRole(button: FrameLayout, checkIcon: ImageView) {
        button.isSelected = true
        checkIcon.visibility = View.VISIBLE
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.select_animation)
        checkIcon.startAnimation(fadeIn)
    }


    private fun deselectRole(button: FrameLayout, checkIcon: ImageView) {
        button.isSelected = false
        checkIcon.clearAnimation()
        checkIcon.visibility = View.GONE
    }

    private fun enableConfirmButton() {
        if (confirmButton.visibility != View.VISIBLE) {
            confirmButton.visibility = View.VISIBLE
            confirmButton.animate()
                .alpha(1f)
                .setDuration(300)
                .start()
        }
    }

}
