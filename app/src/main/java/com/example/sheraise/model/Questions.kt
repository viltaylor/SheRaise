package com.example.sheraise.model

data class Questions(
    val text: String,
    val options: List<String>,
    val correctAnswer: String
)
