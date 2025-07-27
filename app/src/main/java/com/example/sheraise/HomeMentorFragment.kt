package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.adapter.CourseAdapter
import com.example.sheraise.adapter.MentorAdapter
import com.example.sheraise.model.Course
import com.example.sheraise.model.DetailedCourse
import com.example.sheraise.model.Mentor

class HomeMentorFragment : Fragment() {

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_mentor, container, false)

        // Forum button click listener
        val btnForum = view.findViewById<ImageButton>(R.id.btnForum)
        btnForum.setOnClickListener {
            val intent = Intent(requireContext(), ForumActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Continue Courses
        val recyclerRecent = view.findViewById<RecyclerView>(R.id.recyclerRecent)
        recyclerRecent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        courseAdapter = CourseAdapter(getDummyCourses()) { selectedCourse ->
            val detailedCourse = DetailedCourse(
                title = selectedCourse.title,
                mentorName = selectedCourse.mentorName,
                studentCount = 100, // or 0/default
                moduleCount = 5,
                duration = "1h 30m",
                imageResId = selectedCourse.imageResId,
                category = "FRONTEND",
                description = "This is a basic course overview for beginners."
            )

            val detailedFragment = DetailedCourseFragment.newInstance(detailedCourse)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailedFragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerRecent.adapter = courseAdapter

        // Setup Mentors Carousel
        val recyclerMentorship = view.findViewById<RecyclerView>(R.id.recyclerMentorship)
        recyclerMentorship.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        mentorAdapter = MentorAdapter(
            getDummyMentors(),
            onDetailsClick = { mentor ->
                val mentorDetailFragment = MentorDetailFragment.newInstance(mentor)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, mentorDetailFragment)
                    .addToBackStack(null)
                    .commit()
            },
            isFullList = false // This enables carousel spacing
        )
        recyclerMentorship.adapter = mentorAdapter
    }


    private fun getDummyCourses(): List<Course> = listOf(
        Course("Learn Software Dev", "Dinda Smith", R.drawable.banner1),
        Course("Advanced UI Design", "Prashant Kumar", R.drawable.banner1)
    )

    private fun getDummyMentors(): List<Mentor> = listOf(
        Mentor(
            name = "Dinda Smith",
            role = "Frontend Developer",
            courseTitle = "Beginner's Frontend Guide",
            profileImageResId = R.drawable.user_logo,
            tags = "Bachelors",
            rating = 4.8f,
            bio = "Dinda has 5 years of experience building responsive web apps."
        ),
        Mentor(
            name = "Prashant Kumar",
            role = "Full Stack Developer",
            courseTitle = "Full Stack Bootcamp",
            profileImageResId = R.drawable.user_logo,
            tags = "UI Designer",
            rating = 4.6f,
            bio = "Prashant specializes in scalable web systems and RESTful APIs."
        )
    )

}
