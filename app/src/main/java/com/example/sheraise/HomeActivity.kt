package com.example.sheraise

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.adapter.ContinueAdapter
import com.example.sheraise.adapter.MentorAdapter
import com.example.sheraise.model.Course
import com.example.sheraise.model.Mentor
import androidx.recyclerview.widget.PagerSnapHelper

class HomeActivity : AppCompatActivity() {

    private lateinit var continueAdapter: ContinueAdapter
    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val snapHelper = PagerSnapHelper()

        // Setup Continue Courses RecyclerView
        val continueRecycler = findViewById<RecyclerView>(R.id.recyclerContinue)
        continueRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        continueAdapter = ContinueAdapter(getDummyCourses())
        snapHelper.attachToRecyclerView(continueRecycler)
        continueRecycler.adapter = continueAdapter

        // Setup Mentors RecyclerView
        val mentorRecycler = findViewById<RecyclerView>(R.id.recyclerMentor)
        mentorRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mentorAdapter = MentorAdapter(getDummyMentors()) { mentor ->
            Toast.makeText(this, "Clicked: ${mentor.name}", Toast.LENGTH_SHORT).show()
        }
        snapHelper.attachToRecyclerView(mentorRecycler)
        mentorRecycler.adapter = mentorAdapter
    }

    private fun getDummyCourses(): List<Course> = listOf(
        Course(
            title = "Learn Software Development",
            mentorName = "Dinda Smith",
            imageResId = R.drawable.banner1
        ),
        Course(
            title = "Advanced UI Design",
            mentorName = "Prashant Kumar",
            imageResId = R.drawable.banner1
        )
    )


    private fun getDummyMentors(): List<Mentor> = listOf(
        Mentor(
            name = "Dinda Smith",
            role = "Frontend Developer",
            courseTitle = "Frontend Dev Course",
            profileImageResId = R.drawable.user_logo
        ),
        Mentor(
            name = "Prashant Kumar",
            role = "Software Developer",
            courseTitle = "Full Stack Bootcamp",
            profileImageResId = R.drawable.user_logo
        )
    )
}
