package com.example.sheraise.model

// For homepage
data class Course(
    val title: String,
    val mentorName: String,
    val imageResId: Int
)

// For course list fragment
data class DetailedCourse(
    val title: String,
    val mentorName: String,
    val studentCount: Int,
    val moduleCount: Int,
    val duration: String,
    val imageUrl: String,
    val category: String
)


