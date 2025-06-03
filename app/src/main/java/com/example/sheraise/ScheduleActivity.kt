package com.sheraise.mentor

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.kizitonwose.calendarview.CalendarView
import com.sheraise.R

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // back to previous screen
        }

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        // Setup calendar logic (use CalendarView customization here if needed)
    }
}
