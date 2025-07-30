package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sheraise.adapter.CourseAdapter
import com.example.sheraise.adapter.MentorAdapter
import com.example.sheraise.model.Course
import com.example.sheraise.model.Mentor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var mentorAdapter: MentorAdapter
    private lateinit var welcomeText: TextView

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private lateinit var recyclerContinue: RecyclerView
    private lateinit var recyclerMentor: RecyclerView

    private val mentorList = mutableListOf<Mentor>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        welcomeText = view.findViewById(R.id.textWelcome)

        val btnForum = view.findViewById<ImageButton>(R.id.btnForum)
        btnForum.setOnClickListener {
            startActivity(Intent(requireContext(), ForumActivity::class.java))
        }

        recyclerContinue = view.findViewById(R.id.recyclerContinue)
        recyclerContinue.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        recyclerMentor = view.findViewById(R.id.recyclerMentor)
        recyclerMentor.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val profileImage = view.findViewById<ImageView>(R.id.profileImage)
        profileImage.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }

        loadUserName()
        loadUserProfileImage()
        fetchCoursesFromFirestore()

        return view
    }

    private fun loadUserName() {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                val name = document.getString("name")
                welcomeText.text = "Welcome, ${name ?: "User"}!"
            }
            .addOnFailureListener {
                welcomeText.text = "Welcome!"
            }
    }

    private fun loadUserProfileImage() {
        val user = auth.currentUser ?: return
        val imageView = view?.findViewById<ImageView>(R.id.profileImage) ?: return

        firestore.collection("users").document(user.uid)
            .get()
            .addOnSuccessListener { doc ->
                val imageUrl = doc.getString("profileImageUrl")
                if (!imageUrl.isNullOrEmpty()) {
                    Glide.with(this).load(imageUrl).into(imageView)
                }
            }
    }

    private fun fetchCoursesFromFirestore() {
        firestore.collection("courses").get()
            .addOnSuccessListener { documents ->
                val courseList = mutableListOf<Course>()
                mentorList.clear()

                for (doc in documents) {
                    val title = doc.getString("title") ?: continue
                    val mentorName = doc.getString("mentorName") ?: "Unknown"
                    val imageUrl = doc.getString("imageUrl") ?: ""

                    val course = Course(title, mentorName, imageUrl)
                    courseList.add(course)

                    val mentor = Mentor(
                        name = mentorName,
                        role = doc.getString("mentorRole") ?: "Instructor",
                        courseTitle = title,
                        profileImageResId = R.drawable.user_logo,
                        tags = doc.getString("tags") ?: "Educator",
                        rating = doc.getDouble("rating")?.toFloat() ?: 4.5f,
                        bio = doc.getString("mentorBio") ?: "No bio"
                    )
                    mentorList.add(mentor)
                }

                setupCourseAdapter(courseList)
                setupMentorAdapter(mentorList)
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Failed to load courses: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setupCourseAdapter(courses: List<Course>) {
        courseAdapter = CourseAdapter { selectedCourse ->
            val courseDetailFragment = DetailedCourseFragment.newInstance(selectedCourse)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, courseDetailFragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerContinue.adapter = courseAdapter
        courseAdapter.submitList(courses) // ✅ submit list here
    }


    private fun setupMentorAdapter(mentors: List<Mentor>) {
        mentorAdapter = MentorAdapter(
            mentors,
            onDetailsClick = { mentor ->
                val mentorDetailFragment = MentorDetailFragment.newInstance(mentor)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, mentorDetailFragment)
                    .addToBackStack(null)
                    .commit()
            },
            isFullList = false
        )
        recyclerMentor.adapter = mentorAdapter
    }
}
