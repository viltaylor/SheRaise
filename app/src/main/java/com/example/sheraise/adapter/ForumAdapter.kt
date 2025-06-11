package com.example.sheraise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.databinding.ItemForumPostBinding
import com.example.sheraise.model.ForumPost

class ForumAdapter(private val posts: List<ForumPost>) :
    RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {

    inner class ForumViewHolder(private val binding: ItemForumPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: ForumPost) {
            binding.postAuthor.text = "@${post.username}"
            binding.postDate.text = post.date
            binding.postContent.text = post.content

            if (post.imageResId != null) {
                binding.postImage.setImageResource(post.imageResId)
                binding.postImage.visibility = View.VISIBLE
            } else {
                binding.postImage.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val binding = ItemForumPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ForumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size
}
