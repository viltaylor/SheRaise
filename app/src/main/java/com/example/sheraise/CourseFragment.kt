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

    private val adapter = DetailedCourseAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                imageUrl = "https://via.placeholder.com/600x300", // replace with real URLs
                category = "FRONTEND"
            ),
            DetailedCourse(
                title = "How to Create Your Online Course",
                mentorName = "Dinda Smith",
                studentCount = 320,
                moduleCount = 4,
                duration = "45m",
                imageUrl = "https://via.placeholder.com/600x300",
                category = "INTERMEDIATE"
            )
        )
        adapter.submitList(sampleCourses)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
