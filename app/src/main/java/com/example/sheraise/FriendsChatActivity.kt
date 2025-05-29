package com.example.sheraise

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FriendsChatActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnSend: ImageButton
    private lateinit var etMessage: EditText
    private lateinit var onlineIndicator: View
    private lateinit var chatContainer: LinearLayout

    private var isOnline = true // Simulate online/offline status

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_chat)

        btnBack = findViewById(R.id.btnBack)
        btnSend = findViewById(R.id.btnSend)
        etMessage = findViewById(R.id.etMessage)
        onlineIndicator = findViewById(R.id.onlineIndicator)
        chatContainer = findViewById(R.id.chatContainer)

        // Set online/offline indicator
        if (isOnline) {
            onlineIndicator.setBackgroundResource(R.drawable.online_dot)
        } else {
            onlineIndicator.setBackgroundResource(R.drawable.offline_dot)
        }

        // Back button action
        btnBack.setOnClickListener {
            finish()
        }

        // Send button action
        btnSend.setOnClickListener {
            val messageText = etMessage.text.toString().trim()
            if (messageText.isNotEmpty()) {
                addOutgoingMessage(messageText)
                etMessage.text.clear()
            }
        }
    }

    private fun addOutgoingMessage(message: String) {
        val textView = TextView(this)
        textView.text = message
        textView.setPadding(24, 16, 24, 16)
        textView.setTextColor(resources.getColor(android.R.color.white))
        textView.setBackgroundResource(R.drawable.bubble_outgoing)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 0, 16)
        layoutParams.gravity = android.view.Gravity.END
        textView.layoutParams = layoutParams

        chatContainer.addView(textView)
    }
}
