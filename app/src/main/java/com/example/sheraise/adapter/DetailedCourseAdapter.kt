package com.example.sheraise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sheraise.R
import com.example.sheraise.databinding.ItemCourseBinding
import com.example.sheraise.model.Course

class DetailedCourseAdapter(
    private val onItemClick: (Course) -> Unit
) : RecyclerView.Adapter<DetailedCourseAdapter.CourseViewHolder>() {

    private val courseList = mutableListOf<Course>()

    fun submitList(list: List<Course>) {
        courseList.clear()
        courseList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.titleTextView.text = course.title
            binding.mentorNameTextView.text = course.mentorName
            binding.studentsTextView.text = "👥 100 Students"
            binding.modulesTextView.text = "📚 5 Modules"
            binding.durationTextView.text = "⏰ 1h 30m"

            // ✅ Load image from Firestore URL using Glide
            Glide.with(binding.root.context)
                .load(course.imageUrl)
                .placeholder(R.drawable.banner1) // fallback image
                .into(binding.courseImageView)

            binding.root.setOnClickListener {
                onItemClick(course)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courseList[position])
    }

    override fun getItemCount(): Int = courseList.size
}
