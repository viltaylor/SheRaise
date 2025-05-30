package com.example.sheraise

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MentorDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_detail)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val btnBook = findViewById<Button>(R.id.btnBook)
        val tvMentorName = findViewById<TextView>(R.id.tvMentorName)
        val tvMentorCourse = findViewById<TextView>(R.id.tvMentorCourse)
        val imgMentor = findViewById<ImageView>(R.id.imgMentor)

        // Example dummy setup
        tvMentorName.text = "Clara Mentos"
        tvMentorCourse.text = "Math Bachelor"
        imgMentor.setImageResource(R.drawable.mentor1)

        btnBack.setOnClickListener {
            finish()
        }

        btnBook.setOnClickListener {
            Toast.makeText(this, "Booking request sent!", Toast.LENGTH_SHORT).show()
        }
    }
}
