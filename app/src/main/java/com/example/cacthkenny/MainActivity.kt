package com.example.cacthkenny

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cacthkenny.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val dataBase = this.openOrCreateDatabase("WhatsApp", Context.MODE_PRIVATE, null)
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS messages(message_id INTEGER PRIMARY KEY, message_content TEXT, message_date TEXT)")

            dataBase.execSQL("INSERT INTO messages(message_content, message_date) VALUES('this message deleted','10.11.2022')")
            val cursor = dataBase.rawQuery("SELECT* FROM messages",null)

            val mesIDIdx = cursor.getColumnIndex("message_id")
            val mesIdx = cursor.getColumnIndex("message_content")
            val dateIdx = cursor.getColumnIndex("message_date")

            while (cursor.moveToNext()){
                println("ID: ${cursor.getString(mesIDIdx)} | Content: ${cursor.getString(mesIdx)} | Date: ${cursor.getString(dateIdx)}")
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}