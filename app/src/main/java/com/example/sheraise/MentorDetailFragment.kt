package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sheraise.databinding.FragmentMentorDetailBinding
import com.example.sheraise.model.Mentor

class MentorDetailFragment : Fragment() {

    private var _binding: FragmentMentorDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_MENTOR = "arg_mentor"

        fun newInstance(mentor: Mentor): MentorDetailFragment {
            val fragment = MentorDetailFragment()
            val bundle = Bundle().apply {
                putParcelable(ARG_MENTOR, mentor)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mentor: Mentor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mentor = arguments?.getParcelable(ARG_MENTOR)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMentorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mentor?.let {
            binding.textMentorName.text = it.name
            binding.tvTags.text = it.tags
            binding.tvRating.text = "⭐ ${it.rating}"
            binding.tvBio.text = it.bio
            binding.mentorImage.setImageResource(it.profileImageResId)
            binding.backButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
