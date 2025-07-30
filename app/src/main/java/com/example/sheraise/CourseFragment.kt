package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheraise.adapter.DetailedCourseAdapter
import com.example.sheraise.databinding.FragmentCourseBinding
import com.example.sheraise.model.Course
import com.google.firebase.firestore.FirebaseFirestore

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseAdapter: DetailedCourseAdapter
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseAdapter = DetailedCourseAdapter { selectedCourse ->
            val detailFragment = DetailedCourseFragment.newInstance(selectedCourse)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        setupRecyclerView()
        loadCoursesFromFirestore()
    }

    private fun setupRecyclerView() {
        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCourses.adapter = courseAdapter
    }

    private fun loadCoursesFromFirestore() {
        firestore.collection("courses").get()
            .addOnSuccessListener { documents ->
                val courseList = mutableListOf<Course>()
                for (doc in documents) {
                    val title = doc.getString("title") ?: continue
                    val mentorName = doc.getString("mentorName") ?: "Unknown"
                    val imageUrl = doc.getString("imageUrl") ?: ""

                    courseList.add(Course(title, mentorName, imageUrl))
                }
                courseAdapter.submitList(courseList)
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Failed to load courses: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
