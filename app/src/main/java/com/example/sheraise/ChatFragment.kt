package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ChatFragment : Fragment() {

    private lateinit var imgProfile: ImageView
    private lateinit var tvName: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgProfile = view.findViewById(R.id.imgProfile)
        tvName = view.findViewById(R.id.tvName)

        val friendName = arguments?.getString(ARG_FRIEND_NAME)
        val imageResId = arguments?.getInt(ARG_FRIEND_IMAGE)

        tvName.text = friendName
        imageResId?.let { imgProfile.setImageResource(it) }
    }

    companion object {
        private const val ARG_FRIEND_NAME = "friend_name"
        private const val ARG_FRIEND_IMAGE = "friend_image"

        fun newInstance(name: String, imageResId: Int): ChatFragment {
            val fragment = ChatFragment()
            val args = Bundle().apply {
                putString(ARG_FRIEND_NAME, name)
                putInt(ARG_FRIEND_IMAGE, imageResId)
            }
            fragment.arguments = args
            return fragment
        }
    }
}