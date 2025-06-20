package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheraise.adapter.FriendsAdapter
import com.example.sheraise.databinding.FragmentFriendsBinding
import com.example.sheraise.model.Friend

class FriendsFragment : Fragment() {

    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FriendsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FriendsAdapter(getDummyFriends()) { selectedFriend ->
            val chatFragment = ChatFragment.newInstance(selectedFriend.name, selectedFriend.imageResId)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, chatFragment)
                .addToBackStack(null)
                .commit()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvFriends.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFriends.adapter = adapter
    }

    private fun getDummyFriends(): List<Friend> {
        return listOf(
            Friend("Katie Mizu", "Cool. Will let you know ASAP!", R.drawable.user_logo),
            Friend("Jimoni Wong", "Hey, where are you?", R.drawable.user_logo),
            Friend("Jenny Simmone", "Did you know who is this guy?", R.drawable.user_logo),
            Friend("Danniella", "That so cool, keep it up dude", R.drawable.user_logo),
            Friend("Shaniah Drea", "That so cool, keep it up dude", R.drawable.user_logo)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}