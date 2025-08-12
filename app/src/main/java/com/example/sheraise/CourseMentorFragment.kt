package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheraise.adapter.DetailedCourseAdapter
import com.example.sheraise.databinding.FragmentMentorCourseBinding
import com.example.sheraise.model.Course
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CourseMentorFragment : Fragment() {

    private var _binding: FragmentMentorCourseBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseAdapter: DetailedCourseAdapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMentorCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize Firebase (same as LoginActivity)
        FirebaseApp.initializeApp(requireContext())
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Set up adapter with click listener
        courseAdapter = DetailedCourseAdapter { selectedCourse ->
            val detailFragment = DetailedCourseFragment.newInstance(selectedCourse)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, detailFragment)
                .addToBackStack(null)
                .commit()
        }

        setupRecyclerView()

        // Load courses (can be filtered if needed)
        loadCoursesFromFirestore()

        binding.btnAddCourse.setOnClickListener {
            showAddCourseDialog()
        }
    }

    private fun setupRecyclerView() {
        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCourses.adapter = courseAdapter
    }

    private fun loadCoursesFromFirestore() {
        firestore.collection("courses")
            //.whereEqualTo("mentorId", user.uid) // Uncomment to show only user's courses
            .get()
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
                Toast.makeText(requireContext(), "Failed to load: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun showAddCourseDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_add_course, null)

        val etTitle = dialogView.findViewById<EditText>(R.id.etCourseTitle)
        val etMentor = dialogView.findViewById<EditText>(R.id.etMentorName)
        val etImageUrl = dialogView.findViewById<EditText>(R.id.etImageUrl)

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Add Course")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val title = etTitle.text.toString().trim()
                val mentorName = etMentor.text.toString().trim()
                val imageUrl = etImageUrl.text.toString().trim()

                if (title.isEmpty()) {
                    Toast.makeText(requireContext(), "Course title required", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val user = auth.currentUser
                if (user == null) {
                    Toast.makeText(requireContext(), "Not logged in", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val courseData = hashMapOf(
                    "title" to title,
                    "mentorName" to mentorName,
                    "imageUrl" to imageUrl,
                    "mentorId" to user.uid
                )

                firestore.collection("courses")
                    .add(courseData)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Course added", Toast.LENGTH_SHORT).show()
                        loadCoursesFromFirestore()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
