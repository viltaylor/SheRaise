package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.adapter.LearningOptionAdapter
import com.example.sheraise.model.LearningOption

class LearningSelectionActivity : AppCompatActivity() {

    private lateinit var recyclerOptions: RecyclerView
    private lateinit var btnContinue: Button

    private val selectedOptions = mutableSetOf<LearningOption>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_selection)

        recyclerOptions = findViewById(R.id.recyclerOptions)
        btnContinue = findViewById(R.id.btnContinue)

        val options = listOf(
            LearningOption("Ecommerce", R.drawable.logo_ecomerce),
            LearningOption("Coding", R.drawable.logo_coding),
            LearningOption("Marketing", R.drawable.logo_marketing),
            LearningOption("Social Media", R.drawable.logo_socialmed),
            LearningOption("Database", R.drawable.logo_database),
            LearningOption("Entrepreneur", R.drawable.logo_entre)
        )

        recyclerOptions.layoutManager = GridLayoutManager(this, 2)
        recyclerOptions.adapter = LearningOptionAdapter(options, selectedOptions) {
            btnContinue.isEnabled = selectedOptions.isNotEmpty()
        }

        btnContinue.setOnClickListener {
            if (selectedOptions.isNotEmpty()) {
                val intent = Intent(this, StartQuizActivity::class.java)
                intent.putExtra("selected_topics", selectedOptions.map { it.title }.toTypedArray())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select at least one topic to continue.", Toast.LENGTH_SHORT).show()
            }
        }

        // Optional: disable button initially
        btnContinue.isEnabled = false
    }
}
