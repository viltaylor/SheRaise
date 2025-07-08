package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var btnLoginConfirm: Button
    private lateinit var btnGoogle: Button
    private lateinit var btnGuestLogin: Button
    private lateinit var txtSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Ensure this is your correct layout file

        // Initialize views
        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        btnLoginConfirm = findViewById(R.id.btnLoginConfirm)
        btnGoogle = findViewById(R.id.btnGoogle)
        btnGuestLogin = findViewById(R.id.btnGuestLogin)
        txtSignup = findViewById(R.id.txtSignup)

        // Handle login button
        btnLoginConfirm.setOnClickListener {
            val email = emailLogin.text.toString().trim()
            val password = passwordLogin.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                // Dummy success – replace with actual auth logic
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

        // Login as guest
        btnGuestLogin.setOnClickListener {
            Toast.makeText(this, "Logged in as Guest", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Placeholder for Google login
        btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google Login Coming Soon", Toast.LENGTH_SHORT).show()
        }

        // Sign up navigation
        txtSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java)) // Replace with your sign-up activity
        }
    }
}
