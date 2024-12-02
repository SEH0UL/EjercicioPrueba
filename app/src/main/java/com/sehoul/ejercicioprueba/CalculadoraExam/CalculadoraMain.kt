package com.sehoul.ejercicioprueba.CalculadoraExam

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.sehoul.ejercicioprueba.R

class CalculadoraMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_main)

        // Referencias a los elementos del layout
        val primerNumeroEditText = findViewById<EditText>(R.id.ET_primerNum)
        val segundoNumeroEditText = findViewById<EditText>(R.id.ET_segunNum)
        val radioGroupOperaciones = findViewById<RadioGroup>(R.id.rgOperaciones)
        val calcularButton = findViewById<CardView>(R.id.cvCalcular)

        calcularButton.setOnClickListener {
            // Validar que los números no estén vacíos
            val primerNumeroTexto = primerNumeroEditText.text.toString()
            val segundoNumeroTexto = segundoNumeroEditText.text.toString()

            if (primerNumeroTexto.isEmpty() || segundoNumeroTexto.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese ambos números", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val primerNumero = primerNumeroTexto.toDouble()
            val segundoNumero = segundoNumeroTexto.toDouble()

            // Obtener la operación seleccionada
            val operacionSeleccionadaId = radioGroupOperaciones.checkedRadioButtonId
            val operacionSeleccionada = when (operacionSeleccionadaId) {
                R.id.rbSuma -> "Suma"
                R.id.rbResta -> "Resta"
                R.id.rbMulti -> "Multiplicación"
                R.id.rbDivi -> "División"
                R.id.rbRestoDivi -> "Resto"
                else -> ""
            }

            // Enviar los datos a la segunda pantalla
            val intent = Intent(this, Calculadora_result::class.java).apply {
                putExtra("primerNumero", primerNumero)
                putExtra("segundoNumero", segundoNumero)
                putExtra("operacion", operacionSeleccionada)
            }
            startActivity(intent)
        }
    }
}
