package com.example.intentsexample

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var textViewReceived: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textViewReceived = findViewById(R.id.textViewReceived)

        val receivedText = intent.getStringExtra("TEXT_DATA")

        if (receivedText != null) {
            textViewReceived.text = "Полученный текст: $receivedText"
        } else {
            textViewReceived.text = "Текст не получен"
        }
    }
}