package com.example.sheraise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.databinding.ItemCourseBinding
import com.example.sheraise.model.DetailedCourse

class DetailedCourseAdapter(
    private val onItemClick: (DetailedCourse) -> Unit
) : RecyclerView.Adapter<DetailedCourseAdapter.CourseViewHolder>() {

    private val courseList = mutableListOf<DetailedCourse>()

    fun submitList(list: List<DetailedCourse>) {
        courseList.clear()
        courseList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: DetailedCourse) {
            binding.titleTextView.text = course.title
            binding.mentorNameTextView.text = course.mentorName
            binding.studentsTextView.text = "${course.studentCount} Students"
            binding.modulesTextView.text = "${course.moduleCount} Modules"
            binding.durationTextView.text = course.duration

            // Local drawable image
            binding.courseImageView.setImageResource(course.imageResId)

            // Handle item click
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
