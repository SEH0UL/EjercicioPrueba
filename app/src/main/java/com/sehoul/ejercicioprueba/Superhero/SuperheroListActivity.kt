package com.sehoul.ejercicioprueba.Superhero

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sehoul.ejercicioprueba.R
import com.sehoul.ejercicioprueba.databinding.ActivitySuperheroListBinding

class SuperheroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperheroListBinding // SOLO DE ESTA CLASE



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySuperheroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            // MOSTRAR RESULTADOS SEGUN CAMBIA (BD LOCAL)
            override fun onQueryTextChange(newText: String?): Boolean {
            return false
            }

        })
    }

    private fun searchByName(query: String) {

    }


}