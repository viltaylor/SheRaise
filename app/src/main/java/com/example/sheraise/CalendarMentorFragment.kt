package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sheraise.adapter.ScheduleAdapter
import com.example.sheraise.model.ScheduleItem

class CalendarMentorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCalendar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val scheduleList = listOf(
            ScheduleItem("Mentor Session", "27 Jul 2025"),
            ScheduleItem("Assignment Review", "30 Jul 2025"),
            ScheduleItem("Open Q&A", "01 Aug 2025")
        )

        recyclerView.adapter = ScheduleAdapter(scheduleList)
    }
}