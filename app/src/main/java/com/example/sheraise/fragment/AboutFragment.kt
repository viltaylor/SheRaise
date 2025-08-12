package com.example.sheraise.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sheraise.R
import com.google.firebase.firestore.FirebaseFirestore

class AboutFragment : Fragment() {

    private lateinit var aboutTextView: TextView
    private val db = FirebaseFirestore.getInstance()
    private var courseId: String? = null

    companion object {
        private const val ARG_COURSE_ID = "course_id"

        fun newInstance(courseId: String): AboutFragment {
            val fragment = AboutFragment()
            val args = Bundle()
            args.putString(ARG_COURSE_ID, courseId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        courseId = arguments?.getString(ARG_COURSE_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        aboutTextView = view.findViewById(R.id.textAbout)
        courseId?.let { loadAboutContent(it) }
        return view
    }

    private fun loadAboutContent(courseId: String) {
        db.collection("courses").document(courseId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    aboutTextView.text = document.getString("description") ?: "No description available."
                } else {
                    aboutTextView.text = "Course not found."
                }
            }
            .addOnFailureListener {
                aboutTextView.text = "Failed to load content."
            }
    }
}