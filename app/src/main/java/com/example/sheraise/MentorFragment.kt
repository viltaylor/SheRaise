package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheraise.adapter.MentorAdapter
import com.example.sheraise.databinding.FragmentMentorBinding
import com.example.sheraise.model.Mentor

class MentorFragment : Fragment() {
    private var _binding: FragmentMentorBinding? = null
    private val binding get() = _binding!!

    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMentorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize MentorAdapter for full vertical list
        mentorAdapter = MentorAdapter(
            mentors = getDummyMentors(),
            onDetailsClick = { mentor ->
                val mentorDetailFragment = MentorDetailFragment.newInstance(mentor)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, mentorDetailFragment)
                    .addToBackStack(null)
                    .commit()
            },
            isFullList = true // ✅ important to trigger full-list layout behavior
        )

        // Set up RecyclerView with vertical layout
        binding.rvMentors.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mentorAdapter
        }
    }

    // Sample mentor data
    private fun getDummyMentors(): List<Mentor> = listOf(
        Mentor("Dinda Smith", "Frontend Developer", "Frontend Dev Course", R.drawable.user_logo, "Bachelor", 4.5f),
        Mentor("Prashant Kumar", "Software Developer", "Full Stack Bootcamp", R.drawable.user_logo, "Expert", 4.8f),
        Mentor("Alisa Ray", "UX Designer", "Design Thinking Essentials", R.drawable.user_logo, "Specialist", 4.6f),
        Mentor("Kevin Zhao", "AI Specialist", "AI for Beginners", R.drawable.user_logo, "PhD", 4.9f)
    )



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
