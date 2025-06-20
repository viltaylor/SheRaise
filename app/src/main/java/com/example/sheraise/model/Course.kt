package com.example.sheraise.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// For homepage
data class Course(
    val title: String,
    val mentorName: String,
    val imageResId: Int
)

// For course list fragment
@Parcelize
data class DetailedCourse(
    val title: String,
    val mentorName: String,
    val studentCount: Int,
    val moduleCount: Int,
    val duration: String,
    val imageResId: Int, // ← now using drawable resource ID
    val category: String,
    val description: String = ""
) : Parcelable



