package com.example.sheraise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheraise.adapter.ForumAdapter
import com.example.sheraise.databinding.ActivityForumBinding
import com.example.sheraise.model.ForumPost

class ForumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForumBinding
    private lateinit var forumAdapter: ForumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        val posts = listOf(
            ForumPost(
                username = "@johnny",
                date = "6/30/22",
                content = "Just a sample forum post to show structure.",
                imageResId = R.drawable.banner1
            ),
            ForumPost(
                username = "@janes",
                date = "6/30/22",
                content = "This one doesn’t have an image!"
            ),
            ForumPost(
                username = "@johnny",
                date = "6/30/22",
                content = "Just a sample forum post to show structure.",
                imageResId = R.drawable.banner1
            ),
            ForumPost(
                username = "@janes",
                date = "6/30/22",
                content = "This one doesn’t have an image!"
            )
        )

        forumAdapter = ForumAdapter(posts)
        binding.forumRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ForumActivity)
            adapter = forumAdapter
        }
    }
}
