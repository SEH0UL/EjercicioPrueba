package com.sehoul.ejercicioprueba.MessageApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.sehoul.ejercicioprueba.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnSend = findViewById<Button>(R.id.buttonSend)
        var userText = findViewById<EditText>(R.id.etName)

        btnSend.setOnClickListener{
            Log.i("Sehoul", "Sending")
            var name = userText.text.toString()
            if (name.isNotEmpty()){
                var textIntent = Intent(this, PruebaEjercicio2Pantalla::class.java)
                textIntent.putExtra("extra_message", name)
                startActivity(textIntent)
            }
        }
    }
}