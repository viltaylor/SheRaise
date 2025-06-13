package com.example.sheraise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.R
import com.example.sheraise.model.Mentor

class MentorAdapter(
    private val mentors: List<Mentor>,
    private val onDetailsClick: (Mentor) -> Unit
) : RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    inner class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.mentorImage)
        val textMentorName: TextView = itemView.findViewById(R.id.textMentorName)
        val textMentorRole: TextView = itemView.findViewById(R.id.textMentorRole)
        val textCourseTitle: TextView = itemView.findViewById(R.id.textCourseTitle)
        val btnShowDetails: Button = itemView.findViewById(R.id.btnShowDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mentor, parent, false)
        return MentorViewHolder(view)
    }

    private fun Context.dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        val mentor = mentors[position]
        holder.textMentorName.text = mentor.name
        holder.textMentorRole.text = mentor.role
        holder.textCourseTitle.text = mentor.courseTitle
        holder.imgProfile.setImageResource(R.drawable.user_logo)

        holder.btnShowDetails.setOnClickListener {
            onDetailsClick(mentor)
        }

        // Set margin for first and last items
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        val sideMargin = holder.itemView.context.dpToPx(8)
        val edgePadding = holder.itemView.context.dpToPx(8)

        layoutParams.marginStart = if (position == 0) edgePadding else sideMargin
        layoutParams.marginEnd = if (position == itemCount - 1) edgePadding else 0
        holder.itemView.layoutParams = layoutParams
    }


    override fun getItemCount(): Int = mentors.size
}