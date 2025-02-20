package com.sehoul.ejercicioprueba

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sehoul.ejercicioprueba.BoardgamesApp.BoardgamesActivity
import com.sehoul.ejercicioprueba.CalculadoraExam.CalculadoraMain
import com.sehoul.ejercicioprueba.IMCapp.IMCactivity
import com.sehoul.ejercicioprueba.MessageApp.MainActivity
import com.sehoul.ejercicioprueba.PruebaExamen.PruebaExamen_Main
import com.sehoul.ejercicioprueba.Settings.SettingsActivity
import com.sehoul.ejercicioprueba.Superhero.SuperheroListActivity

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        var btnMessageApp = findViewById<Button>(R.id.btnMessageApp)
        var btnIMCapp = findViewById<Button>(R.id.btnIMCapp)
        var btnBoardgamesApp = findViewById<Button>(R.id.btnBoardgamesApp)
        var btnPruebaExamen = findViewById<Button>(R.id.btnPruebaExamen)
        var btnCalculadora = findViewById<Button>(R.id.btnCalculadora)
        var SuperHeroApp = findViewById<Button>(R.id.SuperHeroApp)
        var SettingsApp = findViewById<Button>(R.id.SettingsApp)

        btnMessageApp.setOnClickListener{navigateToMessageApp()}
        btnBoardgamesApp.setOnClickListener{ navigateToBoardgamesApp() }
        btnIMCapp.setOnClickListener{navigateToIMCapp()}
        btnPruebaExamen.setOnClickListener { navigateToPruebaExamen() }
        btnCalculadora.setOnClickListener { navigateToCalculadoraMain() }
        SuperHeroApp.setOnClickListener{ navigateToSuperheroListActivity() }
        SettingsApp.setOnClickListener{ navigateToSettingsApp() }
    }

    private fun navigateToSettingsApp() {
        var intent = Intent (this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperheroListActivity() {
        var intent = Intent (this, SuperheroListActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToCalculadoraMain() {
        var intent = Intent (this, CalculadoraMain::class.java)
        startActivity(intent)
    }

    private fun navigateToPruebaExamen(){
        var intent = Intent (this, PruebaExamen_Main::class.java)
        startActivity(intent)
    }

    private fun navigateToBoardgamesApp(){
        var intent = Intent(this, BoardgamesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMessageApp(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMCapp(){
        var intent = Intent(this, IMCactivity::class.java)
        startActivity(intent)
    }
}