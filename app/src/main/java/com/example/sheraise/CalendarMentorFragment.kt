package com.example.sheraise

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.core.net.toUri

class CalendarMentorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jobs, container, false)

        val jobUrl = "https://id.jobstreet.com/" // ✅ Replace with your preferred job site
        val btnFindJob = view.findViewById<Button>(R.id.btnFindJob)

        btnFindJob.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(jobUrl))
            startActivity(intent)
        }

        return view
    }
}
