package com.example.sheraise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.R
import com.example.sheraise.model.LearningOption

class LearningOptionAdapter(
    private val options: List<LearningOption>,
    private val selectedOptions: MutableSet<LearningOption>,
    private val onSelectionChanged: () -> Unit
) : RecyclerView.Adapter<LearningOptionAdapter.OptionViewHolder>() {

    inner class OptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.iconView)
        val title: TextView = view.findViewById(R.id.titleText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_learning_option, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val option = options[position]
        holder.title.text = option.title
        holder.icon.setImageResource(option.iconResId)

        holder.itemView.alpha = if (selectedOptions.contains(option)) 0.5f else 1.0f

        holder.itemView.setOnClickListener {
            if (selectedOptions.contains(option)) {
                selectedOptions.remove(option)
            } else {
                selectedOptions.add(option)
            }
            notifyItemChanged(position)
            onSelectionChanged()
        }
    }

    override fun getItemCount(): Int = options.size
}
