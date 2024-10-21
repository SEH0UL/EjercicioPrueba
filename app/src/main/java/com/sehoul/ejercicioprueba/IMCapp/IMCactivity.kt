package com.sehoul.ejercicioprueba.IMCapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.RangeSlider
import com.sehoul.ejercicioprueba.MessageApp.ResultActivity
import com.sehoul.ejercicioprueba.R
import java.lang.Math.pow
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class IMCactivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var tvWeight: TextView
    private lateinit var btnSubstractWeight: CardView
    private lateinit var btnAddWeight: CardView

    private lateinit var tvEdad: TextView
    private lateinit var btnSubstracEdad: CardView
    private lateinit var btnAddEdad: CardView

    private  lateinit var btnCalculate: CardView

    private var currentWeight: Int = 60
    private var currentEdad: Int = 20
    private var currentHeight: Int = 120


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        initComponents()
        initListeners()
        setWeight()
        setEdad()
    }


    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.txtPeso)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvEdad = findViewById(R.id.txtEdad)
        btnSubstracEdad = findViewById(R.id.btnSubstracEdad)
        btnAddEdad = findViewById(R.id.btnAddEdad)
        btnCalculate = findViewById(R.id.viewConfirmar)
    }

    private fun initListeners() {
        viewMale.setOnClickListener { setComponentColorMale() }
        viewFemale.setOnClickListener { setComponentColorFemale() }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }


        btnSubstractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }
        btnAddWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }
        btnSubstracEdad.setOnClickListener{
            currentEdad -= 1
            setEdad()
        }
        btnAddEdad.setOnClickListener{
            currentEdad += 1
            setEdad()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            Log.i("IMC", "El IMC es $result")
            navigateToResult(result)
        }

    }

    private fun navigateToResult(result: Double) {
        var intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }

    private fun calculateIMC():Double {
        val dfs = DecimalFormatSymbols()
        dfs .decimalSeparator = '.'
        val df = DecimalFormat("#.##")
        df.decimalFormatSymbols = dfs
        val imc = currentWeight / pow(currentHeight.toDouble() /100, 2.0)

        return df.format(imc).toDouble()
    }

    private fun setWeight() { tvWeight.text = currentWeight.toString() }
    private fun setEdad() { tvEdad.text= currentEdad.toString() }
    private fun setComponentColorFemale() {
        if (!isFemaleSelected) {
            viewFemale.setCardBackgroundColor(getColor(R.color.background_component_selected))
            viewMale.setCardBackgroundColor(getColor(R.color.background_component))
            isMaleSelected = false
            isFemaleSelected = true
        }
    }

    private fun setComponentColorMale() {
        if (!isMaleSelected) {
            viewMale.setCardBackgroundColor(getColor(R.color.background_component_selected))
            viewFemale.setCardBackgroundColor(getColor(R.color.background_component))
            isMaleSelected = true
            isFemaleSelected = false
        }

    }

}