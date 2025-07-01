package com.example.sheraise.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mentor(
    val name: String,
    val role: String,
    val courseTitle: String,
    val profileImageResId: Int,
    val tags: String,
    val rating: Float,
    val bio: String = ""
) : Parcelable
