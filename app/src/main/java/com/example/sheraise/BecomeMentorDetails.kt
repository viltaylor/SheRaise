package com.sheraise.ui.mentor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.sheraise.R

class MentorInfoActivity : AppCompatActivity() {

    private lateinit var fullName: EditText
    private lateinit var bio: EditText
    private lateinit var certificate: EditText
    private lateinit var spinnerExpertise: Spinner
    private lateinit var continueBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_info)

        fullName = findViewById(R.id.etFullName)
        bio = findViewById(R.id.etBio)
        certificate = findViewById(R.id.etCertificate)
        spinnerExpertise = findViewById(R.id.spExpertise)
        continueBtn = findViewById(R.id.btnContinue)

        // Populate spinner
        val expertiseOptions = arrayOf(
            "Select your expertise",
            "Web Development",
            "Digital Marketing",
            "Public Speaking",
            "UI/UX Design",
            "Business & Finance"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, expertiseOptions)
        spinnerExpertise.adapter = adapter

        continueBtn.setOnClickListener {
            // Do form validation or go to next page
            Toast.makeText(this, "Submitted successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}
