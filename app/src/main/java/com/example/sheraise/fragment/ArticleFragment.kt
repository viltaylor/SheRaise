package com.example.sheraise.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sheraise.R
import com.google.firebase.firestore.FirebaseFirestore

class ArticleFragment : Fragment() {

    private lateinit var articleTextView: TextView
    private val db = FirebaseFirestore.getInstance()
    private var courseId: String? = null

    companion object {
        private const val ARG_COURSE_ID = "course_id"

        fun newInstance(courseId: String): ArticleFragment {
            val fragment = ArticleFragment()
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
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        articleTextView = view.findViewById(R.id.articleTextView)
        courseId?.let { loadArticle(it) }
        return view
    }

    private fun loadArticle(courseId: String) {
        db.collection("courses").document(courseId)
            .get()
            .addOnSuccessListener { document ->
                articleTextView.text = document.getString("articleUrl") ?: "No article available."
            }
            .addOnFailureListener {
                articleTextView.text = "Failed to load article."
            }
    }
}
