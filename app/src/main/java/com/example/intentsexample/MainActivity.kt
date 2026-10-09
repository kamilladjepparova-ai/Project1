package com.example.intentsexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var btnOpenSecondActivity: Button
    private lateinit var btnCallFriend: Button
    private lateinit var btnShareText: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.editTextInput)
        btnOpenSecondActivity = findViewById(R.id.btnOpenSecondActivity)
        btnCallFriend = findViewById(R.id.btnCallFriend)
        btnShareText = findViewById(R.id.btnShareText)

        // Явный Intent - открытие второй Activity
        btnOpenSecondActivity.setOnClickListener {
            val text = editTextInput.text.toString().trim()

            if (text.isEmpty()) {
                Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("TEXT_DATA", text)
            startActivity(intent)
        }

        // Неявный Intent - звонок
        btnCallFriend.setOnClickListener {
            val phoneNumber = editTextInput.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Введите номер телефона", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Приложение для звонков не найдено", Toast.LENGTH_SHORT).show()
            }
        }

        // Системный Intent - поделиться текстом
        btnShareText.setOnClickListener {
            val text = editTextInput.text.toString().trim()

            if (text.isEmpty()) {
                Toast.makeText(this, "Введите текст для отправки", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Поделиться текстом")

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, "Поделиться через..."))
            } else {
                Toast.makeText(this, "Нет приложений для отправки", Toast.LENGTH_SHORT).show()
            }
        }
    }
}