package com.example.sheraise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sheraise.R
import com.example.sheraise.databinding.ItemContinueBinding
import com.example.sheraise.model.Course

class CourseAdapter(
    private val onItemClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private val courses = mutableListOf<Course>()

    fun submitList(newCourses: List<Course>) {
        courses.clear()
        courses.addAll(newCourses)
        notifyDataSetChanged()
    }

    inner class CourseViewHolder(private val binding: ItemContinueBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course, position: Int) {
            binding.textTitle.text = course.title
            binding.textMentorName.text = course.mentorName

            // ✅ Same Glide logic as the working DetailedCourseAdapter
            Glide.with(binding.root.context)
                .load(course.imageUrl)
                .placeholder(R.drawable.banner1)
                .into(binding.imageCourse)

            binding.root.setOnClickListener {
                onItemClick(course)
            }

            // ✅ Preserve horizontal margins for carousel
            val layoutParams = binding.root.layoutParams
            if (layoutParams is ViewGroup.MarginLayoutParams) {
                val context = binding.root.context
                val sideMargin = (8 * context.resources.displayMetrics.density).toInt()
                val edgePadding = (8 * context.resources.displayMetrics.density).toInt()

                layoutParams.marginStart = if (position == 0) edgePadding else sideMargin
                layoutParams.marginEnd = if (position == itemCount - 1) edgePadding else 0
                binding.root.layoutParams = layoutParams
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemContinueBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position], position)
    }

    override fun getItemCount(): Int = courses.size
}
