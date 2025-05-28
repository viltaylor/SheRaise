package com.example.sheraise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.R
import com.example.sheraise.model.Course

class ContinueAdapter(private val courses: List<Course>) :
    RecyclerView.Adapter<ContinueAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.courseImage)
        val titleView: TextView = itemView.findViewById(R.id.courseTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_continue, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.titleView.text = course.title

        course.imageResId?.let {
            holder.imageView.setImageResource(it)
        } ?: run {
            // set a placeholder image if imageResId is null
            holder.imageView.setImageResource(R.drawable.banner1)
        }
    }


    override fun getItemCount(): Int = courses.size
}
