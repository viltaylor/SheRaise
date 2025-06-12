package com.sheraise.ui.chat

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.sheraise.R

class FriendChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_chat)

        val backBtn = findViewById<ImageButton>(R.id.btnBack)
        val sendBtn = findViewById<ImageButton>(R.id.btnSend)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val chatContainer = findViewById<LinearLayout>(R.id.chatContainer)

        backBtn.setOnClickListener { finish() }

        sendBtn.setOnClickListener {
            val message = etMessage.text.toString()
            if (message.isNotEmpty()) {
                val newMessage = TextView(this).apply {
                    text = message
                    setBackgroundResource(R.drawable.bubble_outgoing)
                    setPadding(24, 16, 24, 16)
                    setTextColor(resources.getColor(android.R.color.white))
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(0, 8, 0, 8)
                    params.gravity = android.view.Gravity.END
                    layoutParams = params
                }
                chatContainer.addView(newMessage)
                etMessage.text.clear()
            }
        }
    }
}
