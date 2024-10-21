package com.sehoul.ejercicioprueba

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PruebaEjercicio2Pantalla : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_ejercicio2_pantalla)

        var TVMessage = findViewById<TextView>(R.id.TVMessage)
        var mensaje : String = intent.extras?.getString("extra_message").orEmpty()

        var btnSend = findViewById<Button>(R.id.buttonSend)
        var userText = findViewById<EditText>(R.id.etName)

        TVMessage.text = "Su mensaje es: $mensaje"

        btnSend.setOnClickListener{
            Log.i("Sehoul", "Sending")
            var name = userText.text.toString()
            if (name.isNotEmpty()){
                var textIntent : String = intent.extras?.getString("extra_message").orEmpty()
                TVMessage.text = "Su mensaje es: $name"
            }
        }
    }
}