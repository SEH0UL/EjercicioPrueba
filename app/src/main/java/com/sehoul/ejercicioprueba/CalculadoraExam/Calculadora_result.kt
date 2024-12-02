package com.sehoul.ejercicioprueba.CalculadoraExam

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.sehoul.ejercicioprueba.R

class Calculadora_result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_result)

        // Referencias a los elementos del layout
        val tvTipoOperacion = findViewById<TextView>(R.id.tvTipoOperacion)
        val tvOperacion = findViewById<TextView>(R.id.tvOperacion)
        val ET_segunNum = findViewById<TextView>(R.id.ET_segunNum)
        val nuevaOperacionButton = findViewById<CardView>(R.id.cvnuevaOperacion)


        // Recuperar los datos del intent
        val primerNumero = intent.getDoubleExtra("primerNumero", 0.0)
        val segundoNumero = intent.getDoubleExtra("segundoNumero", 0.0)
        val operacion = intent.getStringExtra("operacion") ?: ""

        // Calcular el resultado y mostrar la operación
        val resultado = when (operacion) {
            "Suma" -> primerNumero + segundoNumero
            "Resta" -> primerNumero - segundoNumero
            "Multiplicación" -> primerNumero * segundoNumero
            "División" -> if (segundoNumero != 0.0) primerNumero / segundoNumero else Double.NaN
            "Resto" -> if (segundoNumero != 0.0) primerNumero % segundoNumero else Double.NaN
            else -> Double.NaN
        }

        tvTipoOperacion.text = ":$operacion"
        tvOperacion.text = "$primerNumero ${getSimboloOperacion(operacion)} $segundoNumero"
        ET_segunNum.text = "$resultado"

        // Botón para realizar una nueva operación
        nuevaOperacionButton.setOnClickListener {
            val intent = Intent(this, CalculadoraMain::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getSimboloOperacion(operacion: String): String {
        return when (operacion) {
            "Suma" -> "+"
            "Resta" -> "-"
            "Multiplicación" -> "×"
            "División" -> "÷"
            "Resto" -> "%"
            else -> ""
        }
    }
}
