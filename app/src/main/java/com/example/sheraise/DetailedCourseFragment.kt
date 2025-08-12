package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sheraise.adapter.ViewPagerAdapter
import com.example.sheraise.databinding.FragmentDetailedCourseBinding
import com.example.sheraise.model.Course
import com.google.android.material.tabs.TabLayoutMediator

class DetailedCourseFragment : Fragment() {

    private var _binding: FragmentDetailedCourseBinding? = null
    private val binding get() = _binding!!

    private var course: Course? = null

    companion object {
        private const val ARG_COURSE = "arg_course"

        fun newInstance(course: Course): DetailedCourseFragment {
            val fragment = DetailedCourseFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_COURSE, course)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        course = arguments?.getParcelable(ARG_COURSE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTopContent()
        setupTabs()
        setupBackButton()
    }

    private fun setupTopContent() {
        course?.let { course ->
            binding.courseTitleTextView.text = course.title
            binding.mentorNameTextView.text = course.mentorName
            binding.mentorRoleTextView.text = "Software Developer" // placeholder
            binding.ratingTextView.text = "⭐ 4.5 (500 Reviews)"    // static or optional
            binding.levelTextView.text = "Master 🔥"               // static or optional
            binding.studentCountTextView.text = "👥 100 Student"
            binding.moduleCountTextView.text = "📚 5 Modul"
            binding.durationTextView.text = "⏰ 1h 30m"

            Glide.with(this)
                .load(course.imageUrl)
                .into(binding.courseImageView) // Make sure your layout includes this ImageView
        }
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(this, course?.id ?: "")
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "About"
                1 -> "Video"
                2 -> "Article"
                else -> ""
            }
        }.attach()
    }



    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
