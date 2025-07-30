package com.example.sheraise

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class ProfileFragment : Fragment() {

    // UI
    private lateinit var backButton: Button
    private lateinit var profileImageView: ImageView
    private lateinit var btnChangeImage: Button
    private lateinit var textName: TextView
    private lateinit var textEmail: TextView
    private lateinit var btnEditName: Button
    private lateinit var btnLogout: Button

    // Firebase
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val storage by lazy { FirebaseStorage.getInstance() }

    // Image picker
    private var imageUri: Uri? = null
    private val IMAGE_REQ = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind views
        val backButton = view.findViewById<ImageView>(R.id.btnBack)
        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        profileImageView = view.findViewById(R.id.profileImageView)
        btnChangeImage   = view.findViewById(R.id.btnChangeImage)
        textName         = view.findViewById(R.id.textViewName)
        textEmail        = view.findViewById(R.id.textViewEmail)
        btnEditName      = view.findViewById(R.id.btnEditName)
        btnLogout        = view.findViewById(R.id.btnLogout)

        // Load current profile data
        loadUserData()

        // Image click OR button click → pick image
        profileImageView.setOnClickListener { pickImage() }
        btnChangeImage.setOnClickListener { pickImage() }

        // Edit display‑name
        btnEditName.setOnClickListener { showEditNameDialog() }

        // Logout
        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    /* ──────────────────────────────────────────────────────────────── */
    /*                       Data Loading / Saving                      */
    /* ──────────────────────────────────────────────────────────────── */

    private fun loadUserData() {
        val user = auth.currentUser ?: return
        // Email from FirebaseAuth
        textEmail.text = user.email

        firestore.collection("users").document(user.uid).get()
            .addOnSuccessListener { doc ->
                textName.text = doc.getString("name") ?: "User"

                doc.getString("profileImageUrl")?.let { url ->
                    Glide.with(this).load(url).into(profileImageView)
                }
            }
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQ)
    }

    // Upload selected image → Firebase Storage, then save URL in Firestore
    private fun uploadImage(uri: Uri) {
        val uid = auth.currentUser?.uid ?: return
        val ref = storage.reference.child("profileImages/$uid.jpg")

        ref.putFile(uri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { downloadUri ->
                    firestore.collection("users").document(uid)
                        .update("profileImageUrl", downloadUri.toString())
                        .addOnSuccessListener {
                            Glide.with(this).load(downloadUri).into(profileImageView)
                            Toast.makeText(requireContext(), "Profile photo updated!", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Upload failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Dialog to edit name
    private fun showEditNameDialog() {
        val editText = EditText(requireContext()).apply {
            setText(textName.text)
            setSelection(text.length)
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Edit Name")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) saveName(newName)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveName(newName: String) {
        val uid = auth.currentUser?.uid ?: return
        firestore.collection("users").document(uid)
            .update("name", newName)
            .addOnSuccessListener {
                textName.text = newName
                Toast.makeText(requireContext(), "Name updated", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    /* ──────────────────────────────────────────────────────────────── */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQ && resultCode == AppCompatActivity.RESULT_OK) {
            data?.data?.let { uri ->
                imageUri = uri
                profileImageView.setImageURI(uri)   // preview instantly
                uploadImage(uri)                    // then upload
            }
        }
    }
}
