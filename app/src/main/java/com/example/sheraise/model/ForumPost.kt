package com.example.sheraise.model

data class ForumPost(
    val username: String,
    val date: String,
    val content: String,
    val imageResId: Int? = null,
)
