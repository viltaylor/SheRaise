package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheraise.adapter.DetailedCourseAdapter
import com.example.sheraise.databinding.FragmentCourseBinding
import com.example.sheraise.model.DetailedCourse

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DetailedCourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DetailedCourseAdapter { selectedCourse ->
            // ✅ Use the proper factory method for consistency
            val detailedFragment = DetailedCourseFragment.newInstance(selectedCourse)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailedFragment)
                .addToBackStack(null)
                .commit()
        }

        setupRecyclerView()
        loadDummyCourses()
    }

    private fun setupRecyclerView() {
        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCourses.adapter = adapter
    }

    private fun loadDummyCourses() {
        val sampleCourses = listOf(
            DetailedCourse(
                title = "Beginner's Guide To Becoming A Professional Frontend Developer",
                mentorName = "Dinda Smith",
                studentCount = 500,
                moduleCount = 5,
                duration = "1h 30m",
                imageResId = R.drawable.banner1,
                category = "FRONTEND",
                description = "A step-by-step beginner's guide to start your journey as a professional frontend developer."
            ),
            DetailedCourse(
                title = "How to Create Your Online Course",
                mentorName = "Dinda Smith",
                studentCount = 320,
                moduleCount = 4,
                duration = "45m",
                imageResId = R.drawable.banner1,
                category = "INTERMEDIATE",
                description = "Learn to plan, record, and publish your own online courses with practical tools and strategies."
            ),
            DetailedCourse(
                title = "Advanced Design Thinking",
                mentorName = "Dinda Smith",
                studentCount = 120,
                moduleCount = 6,
                duration = "1h 15m",
                imageResId = R.drawable.banner1,
                category = "ADVANCED",
                description = "Master the process of creative problem-solving and product development using design thinking."
            )
        )

        adapter.submitList(sampleCourses)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
