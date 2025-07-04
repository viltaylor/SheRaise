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
        Mentor(
            name = "Dinda Smith",
            role = "Frontend Developer",
            courseTitle = "Frontend Dev Course",
            profileImageResId = R.drawable.user_logo,
            tags = "Bachelor",
            rating = 4.5f,
            bio = """
            Relevant Certification: Certified Web Developer
            Employment History: Frontend Engineer at Gojek

            Email: dinda.smith@example.com
            Phone: +62 812-3456-7890
            LinkedIn: linkedin.com/in/dindasmith
        """.trimIndent(),
        ),
        Mentor(
            name = "Prashant Kumar",
            role = "Software Developer",
            courseTitle = "Full Stack Bootcamp",
            profileImageResId = R.drawable.user_logo,
            tags = "Expert",
            rating = 4.8f,
            bio = """
            Relevant Certification: Java & Spring Expert
            Employment History: Backend Lead at Tokopedia

            Email: prashant.k@example.com
            Phone: +62 813-2345-6789
            LinkedIn: linkedin.com/in/prashantk
        """.trimIndent(),
        ),
        Mentor(
            name = "Alisa Ray",
            role = "UX Designer",
            courseTitle = "Design Thinking Essentials",
            profileImageResId = R.drawable.user_logo,
            tags = "Specialist",
            rating = 4.6f,
            bio = """
            Relevant Certification: UI/UX Mastery
            Employment History: Senior UX Designer at Grab

            Email: alisa.ray@example.com
            Phone: +62 819-8765-4321
            LinkedIn: linkedin.com/in/alisaray
        """.trimIndent(),
        )
    )


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
