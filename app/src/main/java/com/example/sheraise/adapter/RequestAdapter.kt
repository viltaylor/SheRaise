package com.example.sheraise.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.R
import com.example.sheraise.model.Student

class RequestAdapter(
    private val students: List<Student>,
    private val onDetailsClick: (Student) -> Unit
) : RecyclerView.Adapter<RequestAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)
        val textStudentName: TextView = itemView.findViewById(R.id.tvStudentName)
        val textStudentRole: TextView = itemView.findViewById(R.id.tvStudentRole)
        val btnShowDetails: Button? = itemView.findViewById(R.id.btnShowDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_request, parent, false)
        return StudentViewHolder(view)
    }

    private fun Context.dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.textStudentName.text = student.name
        holder.textStudentRole.text = student.role
        holder.imgProfile.setImageResource(student.profileImageResId)

        // Click listeners
        holder.itemView.setOnClickListener {
            onDetailsClick(student)
        }

        holder.btnShowDetails?.setOnClickListener {
            onDetailsClick(student)
        }

        // Set margins
        val context = holder.itemView.context
        val layoutParams = holder.itemView.layoutParams as? ViewGroup.MarginLayoutParams
        layoutParams?.let {
            val sideMargin = context.dpToPx(8)
            val edgePadding = context.dpToPx(8)
            it.marginStart = if (position == 0) edgePadding else sideMargin
            it.marginEnd = if (position == itemCount - 1) edgePadding else 0
            holder.itemView.layoutParams = it
        }
    }

    override fun getItemCount(): Int = students.size
}