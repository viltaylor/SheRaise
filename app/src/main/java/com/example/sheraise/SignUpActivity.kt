package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var googleSignUpButton: Button
    private lateinit var loginText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Bind views
        nameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword)
        signUpButton = findViewById(R.id.btnSignUp)
        googleSignUpButton = findViewById(R.id.btnGoogleSignUp)
        loginText = findViewById(R.id.txtLogin)

        // Sign up button click
        signUpButton.setOnClickListener {
            handleSignUp()
        }

        // Google Sign Up (dummy)
        googleSignUpButton.setOnClickListener {
            Toast.makeText(this, "Google Sign Up coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Already have account → go to Login
        loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleSignUp() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        // TODO: Integrate with backend / Firebase / local DB
        Toast.makeText(this, "Signed up as $name!", Toast.LENGTH_SHORT).show()

        // Go to login or home
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
