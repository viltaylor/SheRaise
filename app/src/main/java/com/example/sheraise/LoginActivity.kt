package com.example.sheraise

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var btnLoginConfirm: Button
    private lateinit var btnGoogle: Button
    private lateinit var btnGuestLogin: Button
    private lateinit var txtSignup: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

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
                loginUser(email, password)
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
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                val uid = auth.currentUser?.uid

                if (uid != null) {
                    firestore.collection("users").document(uid).get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                val name = document.getString("name")
                                val role = document.getString("role")

                                Toast.makeText(this, "Welcome $name!", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this, HomeActivity::class.java)
                                intent.putExtra("role", role)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "User data not found in Firestore", Toast.LENGTH_LONG).show()
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Failed to load user info: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    Toast.makeText(this, "User ID not found", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }


}
