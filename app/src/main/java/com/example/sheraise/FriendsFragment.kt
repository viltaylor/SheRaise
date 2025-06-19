package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.adapter.FriendsAdapter
import com.example.sheraise.model.Friend

class FriendsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FriendsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)

        recyclerView = view.findViewById(R.id.rvFriends)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Dummy data
        val friends = listOf(
            Friend(
                name = "Katie Mizu",
                lastMessage = "Cool. Will let you know ASAP!",
                imageResId = R.drawable.user_logo
            ),
            Friend(
                name = "Jimoni Wong",
                lastMessage = "Hey, where are you?",
                imageResId = R.drawable.user_logo),
            Friend(
                name = "Jenny Simmone",
                lastMessage = "Did you know who is this guy?",
                imageResId = R.drawable.user_logo),
            Friend(
                name = "Danniella",
                lastMessage = "That so cool, keep it up dude",
                imageResId = R.drawable.user_logo),
            Friend(
                name = "Shaniah Drea",
                lastMessage = "That so cool, keep it up dude",
                imageResId =    R.drawable.user_logo)
        )

        adapter = FriendsAdapter(friends) { friend ->
            // TODO: Navigate to chat screen
            // Example: startActivity(Intent(context, ChatActivity::class.java))
        }

        recyclerView.adapter = adapter

        return view
    }
}
