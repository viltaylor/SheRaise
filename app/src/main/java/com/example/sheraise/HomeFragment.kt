package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.adapter.CourseAdapter
import com.example.sheraise.adapter.MentorAdapter
import com.example.sheraise.model.Course
import com.example.sheraise.model.Mentor

class HomeFragment : Fragment() {

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Continue Courses
        val recyclerContinue = view.findViewById<RecyclerView>(R.id.recyclerContinue)
        recyclerContinue.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        courseAdapter = CourseAdapter(getDummyCourses()) { /* no-op */ }
        recyclerContinue.adapter = courseAdapter

        // Setup Mentors
        val recyclerMentor = view.findViewById<RecyclerView>(R.id.recyclerMentor)
        recyclerMentor.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        mentorAdapter = MentorAdapter(getDummyMentors()) { mentor ->
            Toast.makeText(requireContext(), "Clicked: ${mentor.name}", Toast.LENGTH_SHORT).show()
        }
        recyclerMentor.adapter = mentorAdapter
    }

    private fun getDummyCourses(): List<Course> = listOf(
        Course("Learn Software Dev", "Dinda Smith", R.drawable.banner1),
        Course("Advanced UI Design", "Prashant Kumar", R.drawable.banner1)
    )

    private fun getDummyMentors(): List<Mentor> = listOf(
        Mentor("Dinda Smith", "Frontend Developer", "Frontend Dev Course", R.drawable.user_logo),
        Mentor("Prashant Kumar", "Software Developer", "Full Stack Bootcamp", R.drawable.user_logo)
    )
}

