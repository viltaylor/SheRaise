package com.example.sheraise.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    val id: String = "",
    val title: String = "",
    val mentorName: String = "",
    val imageUrl: String = "",
    val duration: String = "",      // e.g., "1h 30m"
    val modules: Int = 0,           // e.g., 5
    val students: Int = 0           // e.g., 100
) : Parcelable
