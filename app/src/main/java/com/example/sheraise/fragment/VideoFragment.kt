package com.example.sheraise.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sheraise.R
import com.google.firebase.firestore.FirebaseFirestore
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoFragment : Fragment() {

    private lateinit var courseId: String
    private lateinit var youtubePlayerView: YouTubePlayerView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseId = it.getString(ARG_COURSE_ID, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        youtubePlayerView = view.findViewById(R.id.videoView)

        lifecycle.addObserver(youtubePlayerView)

        loadYouTubeVideo()

        return view
    }

    private fun loadYouTubeVideo() {
        if (courseId.isEmpty()) {
            Toast.makeText(requireContext(), "Invalid course ID", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("courses").document(courseId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val youtubeUrl = document.getString("youtubeLink") ?: ""
                    if (youtubeUrl.isNotEmpty()) {
                        val videoId = extractYouTubeId(youtubeUrl)
                        if (videoId != null) {
                            youtubePlayerView.addYouTubePlayerListener(object :
                                AbstractYouTubePlayerListener() {
                                override fun onReady(youTubePlayer: YouTubePlayer) {
                                    youTubePlayer.loadVideo(videoId, 0f)
                                }
                            })
                        } else {
                            Toast.makeText(requireContext(), "Invalid YouTube URL", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "No YouTube link found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Course not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to load video", Toast.LENGTH_SHORT).show()
            }
    }

    private fun extractYouTubeId(url: String): String? {
        val regex = "(?<=watch\\?v=|/videos/|embed/|youtu.be/)[^#&?\\n]*".toRegex()
        return regex.find(url)?.value
    }

    override fun onDestroyView() {
        super.onDestroyView()
        youtubePlayerView.release()
    }

    companion object {
        private const val ARG_COURSE_ID = "courseId"

        fun newInstance(courseId: String) =
            VideoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_COURSE_ID, courseId)
                }
            }
    }
}
