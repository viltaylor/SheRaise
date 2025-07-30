package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sheraise.adapter.CourseAdapter
import com.example.sheraise.adapter.RequestAdapter
import com.example.sheraise.model.Course
import com.example.sheraise.model.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeMentorFragment : Fragment() {

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var requestAdapter: RequestAdapter
    private lateinit var welcomeText: TextView

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_mentor, container, false)

        welcomeText = view.findViewById(R.id.textWelcome)

        val btnForum = view.findViewById<ImageButton>(R.id.btnForum)
        btnForum.setOnClickListener {
            startActivity(Intent(requireContext(), ForumActivity::class.java))
        }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerRecent = view.findViewById<RecyclerView>(R.id.recyclerRecent)
        recyclerRecent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val recyclerMentorship = view.findViewById<RecyclerView>(R.id.recyclerMentorship)
        recyclerMentorship.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        requestAdapter = RequestAdapter(
            getDummyRequest(),
            onDetailsClick = { student ->
                showRequestPopup(student)
            }
        )
        recyclerMentorship.adapter = requestAdapter
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
                for (doc in documents) {
                    val title = doc.getString("title") ?: continue
                    val mentorName = doc.getString("mentorName") ?: "Unknown"
                    val imageUrl = doc.getString("imageUrl") ?: ""

                    val course = Course(title, mentorName, imageUrl)
                    courseList.add(course)
                }

                setupCourseAdapter(courseList)
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

        val recyclerRecent = view?.findViewById<RecyclerView>(R.id.recyclerRecent)
        recyclerRecent?.adapter = courseAdapter
        courseAdapter.submitList(courses)
    }

    private fun getDummyRequest(): List<Student> = listOf(
        Student(
            name = "Dinda Smith",
            role = "Student",
            profileImageResId = R.drawable.user_logo,
        ),
        Student(
            name = "Prashant Kumar",
            role = "Student",
            profileImageResId = R.drawable.user_logo,
        )
    )

    private fun showRequestPopup(student: Student) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_request_action, null)
        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        val imgStudent = dialogView.findViewById<ImageView>(R.id.imgStudent)
        val tvName = dialogView.findViewById<TextView>(R.id.tvStudentName)
        val btnApprove = dialogView.findViewById<Button>(R.id.btnApprove)
        val btnDecline = dialogView.findViewById<Button>(R.id.btnDecline)

        imgStudent.setImageResource(student.profileImageResId)
        tvName.text = student.name

        btnApprove.setOnClickListener {
            dialog.dismiss()
        }

        btnDecline.setOnClickListener {
            dialog.dismiss()
            showDeclineReasonPopup()
        }

        dialog.show()
    }

    private fun showDeclineReasonPopup() {
        val declineView = layoutInflater.inflate(R.layout.dialog_decline_reason, null)
        val declineDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setView(declineView)
            .create()

        val radioGroup = declineView.findViewById<RadioGroup>(R.id.radioGroupReasons)
        val etOther = declineView.findViewById<EditText>(R.id.etOtherReason)
        val btnSubmit = declineView.findViewById<Button>(R.id.btnSubmitReason)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            etOther.visibility = if (checkedId == R.id.radioOther) View.VISIBLE else View.GONE
        }

        btnSubmit.setOnClickListener {
            val selectedReason = when (radioGroup.checkedRadioButtonId) {
                R.id.radioSchedule -> "Schedule full"
                R.id.radioNotCompatible -> "Not compatible"
                R.id.radioOther -> etOther.text.toString()
                else -> "No reason selected"
            }

            declineDialog.dismiss()
            Toast.makeText(requireContext(), "Declined with reason: $selectedReason", Toast.LENGTH_SHORT).show()
        }

        declineDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}