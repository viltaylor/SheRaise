package com.example.sheraise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.R
import com.example.sheraise.model.Course

class CourseAdapter(
    private val courses: List<Course>,
    private val onItemClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageCourse: ImageView = itemView.findViewById(R.id.imageCourse)
        val textTag: TextView = itemView.findViewById(R.id.textTag)
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textMentorName: TextView = itemView.findViewById(R.id.textMentorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_continue, parent, false)

        return CourseViewHolder(view)
    }

    private fun Context.dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.textTitle.text = course.title
        holder.textMentorName.text = course.mentorName
        holder.imageCourse.setImageResource(course.imageResId)

        holder.itemView.setOnClickListener {
            onItemClick(course)
        }

        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        val context = holder.itemView.context
        val sideMargin = context.dpToPx(8)
        val edgePadding = context.dpToPx(8)

        layoutParams.marginStart = if (position == 0) edgePadding else sideMargin
        layoutParams.marginEnd = if (position == itemCount - 1) edgePadding else 0

        holder.itemView.layoutParams = layoutParams
    }






    override fun getItemCount(): Int = courses.size
}
