package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var googleSignUpButton: Button
    private lateinit var loginText: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Firebase initialization
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Bind views
        nameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword)
        signUpButton = findViewById(R.id.btnSignUp)
        googleSignUpButton = findViewById(R.id.btnGoogleSignUp)
        loginText = findViewById(R.id.txtLogin)

        // Sign up action
        signUpButton.setOnClickListener { handleSignUp() }

        // Google Sign Up placeholder
        googleSignUpButton.setOnClickListener {
            Toast.makeText(this, "Google Sign Up coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Navigate to Login screen
        loginText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun handleSignUp() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val confirmPassword = confirmPasswordEditText.text.toString().trim()

        // Input validation
        if (name.isEmpty()) {
            nameEditText.error = "Name is required"
            nameEditText.requestFocus()
            return
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.error = "Enter a valid email"
            emailEditText.requestFocus()
            return
        }

        if (password.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters"
            passwordEditText.requestFocus()
            return
        }

        if (password != confirmPassword) {
            confirmPasswordEditText.error = "Passwords do not match"
            confirmPasswordEditText.requestFocus()
            return
        }

        // Firebase Auth sign-up
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val uid = auth.currentUser?.uid
                if (uid != null) {
                    val userMap = hashMapOf(
                        "uid" to uid,
                        "name" to name,
                        "email" to email,
                        "role" to "user" // default role
                    )

                    // Store user data in Firestore
                    firestore.collection("users")
                        .document(uid)
                        .set(userMap)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error saving user to Firestore: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(this, "User ID is null after sign-up", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Sign up failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
