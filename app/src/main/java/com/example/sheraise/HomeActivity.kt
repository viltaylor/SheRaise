package com.example.sheraise

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.adapter.MentorAdapter
import com.example.sheraise.model.Course
import com.example.sheraise.model.Mentor
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.sheraise.adapter.CourseAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val snapHelper = PagerSnapHelper()

        // Setup Continue Courses RecyclerView
        val continueRecycler = findViewById<RecyclerView>(R.id.recyclerContinue)
        continueRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        courseAdapter = CourseAdapter(getDummyCourses()) { /* no-op */ }
        continueRecycler.adapter = courseAdapter

        // Setup Mentors RecyclerView
        val mentorRecycler = findViewById<RecyclerView>(R.id.recyclerMentor)
        mentorRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mentorAdapter = MentorAdapter(getDummyMentors()) { mentor ->
            Toast.makeText(this, "Clicked: ${mentor.name}", Toast.LENGTH_SHORT).show()
        }
        mentorRecycler.adapter = mentorAdapter

        // Bottom Navigation Setup using Triple
        val navItems = listOf(
            Triple(R.id.navHome, R.id.navHomeIcon, R.id.navHomeLabel to R.id.navHomeCircle),
            Triple(R.id.navCourse, R.id.navCourseIcon, R.id.navCourseLabel to R.id.navCourseCircle),
            Triple(R.id.navFriends, R.id.navFriendIcon, R.id.navFriendsLabel to R.id.navFriendsCircle),
            Triple(R.id.navMentor, R.id.navMentorIcon, R.id.navMentorLabel to R.id.navMentorCircle),
            Triple(R.id.navJobs, R.id.navJobsIcon, R.id.navJobsLabel to R.id.navJobsCircle)
        )

        navItems.forEach { (layoutId, _, _) ->
            val layout = findViewById<LinearLayout>(layoutId)
            layout.setOnClickListener {
                selectNavItem(layoutId)
            }
        }

        selectNavItem(R.id.navHome) // default selected
    }

    private fun selectNavItem(selectedId: Int) {
        val navItemTuples = listOf(
            Triple(R.id.navHome, R.id.navHomeIcon, R.id.navHomeLabel to R.id.navHomeCircle),
            Triple(R.id.navCourse, R.id.navCourseIcon, R.id.navCourseLabel to R.id.navCourseCircle),
            Triple(R.id.navFriends, R.id.navFriendIcon, R.id.navFriendsLabel to R.id.navFriendsCircle),
            Triple(R.id.navMentor, R.id.navMentorIcon, R.id.navMentorLabel to R.id.navMentorCircle),
            Triple(R.id.navJobs, R.id.navJobsIcon, R.id.navJobsLabel to R.id.navJobsCircle)
        )

        navItemTuples.forEach { (layoutId, iconId, labelCirclePair) ->
            val layout = findViewById<LinearLayout>(layoutId)
            val icon = layout.findViewById<ImageView>(iconId)
            val label = layout.findViewById<TextView>(labelCirclePair.first)
            val circle = layout.findViewById<FrameLayout>(labelCirclePair.second)

            if (layoutId == selectedId) {
                circle.visibility = View.VISIBLE
                circle.setBackgroundResource(R.drawable.nav_selected)
                icon.setColorFilter(ContextCompat.getColor(this, android.R.color.white))
                label.setTextColor(ContextCompat.getColor(this, R.color.pink))
                label.visibility = View.VISIBLE
            } else {
                circle.setBackgroundColor(Color.TRANSPARENT)
                icon.setColorFilter(ContextCompat.getColor(this, android.R.color.black))
                label.setTextColor(ContextCompat.getColor(this, android.R.color.black))
                label.visibility = View.GONE
            }
        }
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
        ),
        Mentor(
            name = "Jonathan",
            role = "Software Engineer",
            courseTitle = "Full Stack Course",
            profileImageResId = R.drawable.user_logo
        )
    )
}
