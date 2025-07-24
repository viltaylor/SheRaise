package com.example.sheraise.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val name: String,
    val role: String,
    val profileImageResId: Int,
) : Parcelable
