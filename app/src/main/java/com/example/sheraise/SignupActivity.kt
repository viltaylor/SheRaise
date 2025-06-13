package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val loginText = findViewById<TextView>(R.id.txtLogin)
        val signUpButton = findViewById<Button>(R.id.btnSignUp)
        val googleButton = findViewById<Button>(R.id.btnGoogle)

        loginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        signUpButton.setOnClickListener {
            // TODO: Add sign-up logic here
            Toast.makeText(this, "Signed up!", Toast.LENGTH_SHORT).show()
        }

        googleButton.setOnClickListener {
            // TODO: Google Sign-In logic
            Toast.makeText(this, "Login with Google clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
