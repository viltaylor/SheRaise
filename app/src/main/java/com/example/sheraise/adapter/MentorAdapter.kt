package com.example.sheraise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.R
import com.example.sheraise.model.Mentor

class MentorAdapter(
    private val mentors: List<Mentor>,
    private val onDetailsClick: (Mentor) -> Unit,
    private val isFullList: Boolean = false // true for MentorFragment, false for HomeFragment
) : RecyclerView.Adapter<MentorAdapter.MentorViewHolder>() {

    inner class MentorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)
        val textMentorName: TextView = itemView.findViewById(R.id.tvMentorName)
        val textMentorRole: TextView = itemView.findViewById(R.id.tvMentorRole)
        val textCourseTitle: TextView? = itemView.findViewById(R.id.textCourseTitle)
        val btnShowDetails: Button? = itemView.findViewById(R.id.btnShowDetails)

        // Only exists in item_mentor_page.xml
        val ratingText: TextView? = itemView.findViewById(R.id.tvRating)
        val tagText: TextView? = itemView.findViewById(R.id.tvMentorTag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val layoutId = if (isFullList) R.layout.item_mentor_page else R.layout.item_mentor
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MentorViewHolder(view)
    }

    private fun Context.dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        val mentor = mentors[position]

        holder.textMentorName.text = mentor.name
        holder.textMentorRole.text = mentor.role
        holder.imgProfile.setImageResource(mentor.profileImageResId)

        holder.textCourseTitle?.text = mentor.courseTitle
        holder.ratingText?.text = "4.9"
        holder.tagText?.text = mentor.tags
        // Make whole item clickable
        holder.itemView.setOnClickListener {
            onDetailsClick(mentor)
        }

// If there's a button (like in item_mentor), make that clickable too
        holder.btnShowDetails?.setOnClickListener {
            onDetailsClick(mentor)
        }


        val layoutParams = holder.itemView.layoutParams as? ViewGroup.MarginLayoutParams
        if (layoutParams != null) {
            if (isFullList) {
                layoutParams.marginStart = 0
                layoutParams.marginEnd = 0
            } else {
                val sideMargin = holder.itemView.context.dpToPx(8)
                val edgePadding = holder.itemView.context.dpToPx(8)
                layoutParams.marginStart = if (position == 0) edgePadding else sideMargin
                layoutParams.marginEnd = if (position == itemCount - 1) edgePadding else 0
            }
            holder.itemView.layoutParams = layoutParams
        }
    }

    override fun getItemCount(): Int = mentors.size
}
