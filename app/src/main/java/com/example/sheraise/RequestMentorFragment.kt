package com.example.sheraise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sheraise.adapter.RequestAdapter
import com.example.sheraise.databinding.FragmentRequestBinding
import com.example.sheraise.model.Student

class RequestMentorFragment : Fragment() {
    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!

    private lateinit var requestAdapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestAdapter = RequestAdapter(
            students = getDummyStudents(),
            onDetailsClick = { student ->
                showRequestPopup(student)
            }
        )

        // Set up RecyclerView with vertical layout
        binding.rvRequest.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = requestAdapter
        }
    }

    // Sample mentor data
    private fun getDummyStudents(): List<Student> = listOf(
        Student(
            name = "Dinda Smith",
            role = "Student",
            profileImageResId = R.drawable.user_logo,
        ),
        Student(
            name = "Prashant Kumar",
            role = "Student",
            profileImageResId = R.drawable.user_logo,
        ),
        Student(
            name = "Alisa Ray",
            role = "Student",
            profileImageResId = R.drawable.user_logo,
        )
    )

    private fun showRequestPopup(student: Student) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_request_action, null)
        val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        val imgStudent = dialogView.findViewById<ImageView>(R.id.imgStudent)
        val tvName = dialogView.findViewById<TextView>(R.id.tvStudentName)
        val btnApprove = dialogView.findViewById<Button>(R.id.btnApprove)
        val btnDecline = dialogView.findViewById<Button>(R.id.btnDecline)

        imgStudent.setImageResource(student.profileImageResId)
        tvName.text = student.name

        btnApprove.setOnClickListener {
            dialog.dismiss()
        }

        btnDecline.setOnClickListener {
            dialog.dismiss()
            showDeclineReasonPopup()
        }

        dialog.show()
    }

    private fun showDeclineReasonPopup() {
        val declineView = layoutInflater.inflate(R.layout.dialog_decline_reason, null)
        val declineDialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setView(declineView)
            .create()

        val radioGroup = declineView.findViewById<RadioGroup>(R.id.radioGroupReasons)
        val etOther = declineView.findViewById<EditText>(R.id.etOtherReason)
        val btnSubmit = declineView.findViewById<Button>(R.id.btnSubmitReason)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            etOther.visibility = if (checkedId == R.id.radioOther) View.VISIBLE else View.GONE
        }

        btnSubmit.setOnClickListener {
            val selectedReason = when (radioGroup.checkedRadioButtonId) {
                R.id.radioSchedule -> "Schedule full"
                R.id.radioNotCompatible -> "Not compatible"
                R.id.radioOther -> etOther.text.toString()
                else -> "No reason selected"
            }

            declineDialog.dismiss()
            Toast.makeText(requireContext(), "Declined with reason: $selectedReason", Toast.LENGTH_SHORT).show()
        }

        declineDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
