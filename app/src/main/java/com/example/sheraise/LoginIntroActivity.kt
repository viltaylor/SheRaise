package com.example.sheraise

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.animation.AnimationUtils

class LoginIntroActivity : AppCompatActivity() {

    private lateinit var studentButton: FrameLayout
    private lateinit var mentorButton: FrameLayout
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

        studentButton = findViewById<FrameLayout>(R.id.studentButton)
        mentorButton = findViewById<FrameLayout>(R.id.mentorButton)
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

    private fun selectRole(button: FrameLayout, checkIcon: ImageView) {
        button.isSelected = true
        checkIcon.visibility = View.VISIBLE
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.select_animation)
        checkIcon.startAnimation(fadeIn)
    }


    private fun deselectRole(button: FrameLayout, checkIcon: ImageView) {
        button.isSelected = false
        val fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                checkIcon.visibility = View.GONE
                checkIcon.clearAnimation() // Clear to prevent lingering effects
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
        checkIcon.startAnimation(fadeOut)
    }


}
