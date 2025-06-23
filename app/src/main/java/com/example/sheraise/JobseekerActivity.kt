package com.example.sheraise

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JobSeekerActivity : AppCompatActivity() {

    private lateinit var jobRecyclerView: RecyclerView
    private lateinit var jobList: ArrayList<Job>
    private lateinit var jobAdapter: JobAdapter
    private lateinit var backButton: ImageButton
    private lateinit var seeAll: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_seeker)

        backButton = findViewById(R.id.btnBack)
        seeAll = findViewById(R.id.tvSeeAll)

        jobRecyclerView = RecyclerView(this)
        jobRecyclerView = findViewById(R.id.jobRecyclerView)
        jobRecyclerView.layoutManager = LinearLayoutManager(this)
        jobRecyclerView.setHasFixedSize(true)

        // Dummy job data
        jobList = arrayListOf(
            Job(
                "Product Designer",
                "Slack",
                "$105k - $180k",
                "Jun 16, 2021",
                "113 applicants",
                "New York",
                "Full-time",
                R.drawable.slack_logo,
                R.color.light_blue
            ),
            Job(
                "Frontend Dev",
                "Netflix",
                "$105k - $180k",
                "Jun 16, 2021",
                "23 applicants",
                "New York",
                "Full-time",
                R.drawable.netflix_logo,
                R.color.light_pink
            ),
            Job(
                "Product Designer",
                "Slack",
                "$105k - $180k",
                "Jun 16, 2021",
                "113 applicants",
                "New York",
                "Full-time",
                R.drawable.slack_logo,
                R.color.light_blue
            ),
            Job(
                "Frontend Dev",
                "Netflix",
                "$105k - $180k",
                "Jun 16, 2021",
                "23 applicants",
                "New York",
                "Full-time",
                R.drawable.netflix_logo,
                R.color.light_pink
            )
        )

        jobAdapter = JobAdapter(jobList)
        jobRecyclerView.adapter = jobAdapter

        // Back button
        backButton.setOnClickListener {
            onBackPressed()
        }

        // See All button
        seeAll.setOnClickListener {
            // Add navigation or logic here
        }
    }
}
