package com.sehoul.ejercicioprueba.Superhero

import android.content.Intent
import android.nfc.NfcAdapter.EXTRA_ID
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.sehoul.ejercicioprueba.R
import com.sehoul.ejercicioprueba.databinding.ActivitySuperheroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperheroListActivity : AppCompatActivity() {

    class SuperHeroListActivity : AppCompatActivity() {
        companion object {
            const val EXTRA_ID = "extra_id"
        }

        private lateinit var binding: ActivitySuperheroListBinding // SOLO DE ESTA CLASE
        private lateinit var retrofit: Retrofit
        private lateinit var adapter: SuperheroAdapter

        // TOKEN a55b575a3f3aa949c7ac1640572b7545

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivitySuperheroListBinding.inflate(layoutInflater)
            setContentView(binding.root)
            retrofit = getRetrofit()
            initUI()
        }

        private fun initUI() {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchByName(query.orEmpty())
                    return false
                }

                // MOSTRAR RESULTADOS SEGUN CAMBIA (BD LOCAL)
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
            adapter = SuperheroAdapter { superheroId ->  navigateToDetail(superheroId) }
            binding.rvSuperhero.setHasFixedSize(true)
            binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
            binding.rvSuperhero.adapter = adapter


        }

        private fun searchByName(query: String) {
            binding.progressBar.isVisible = true
            CoroutineScope(Dispatchers.IO).launch {
                val myResponse: Response<SuperHeroDataResponse> =
                    retrofit.create(ApiService::class.java).getSuperheroes(query)
                if (myResponse.isSuccessful) {
                    Log.i("Consulta", "Funciona :)")
                    val response: SuperHeroDataResponse? = myResponse.body()
                    if (response != null) {
                        Log.i("Cuerpo de la consulta", response.toString())
                        // HILO PRINCIPAL PARA PODER HACERLO, SINO LANZA ERROR
                        runOnUiThread {
                            adapter.updateList(response.superheroes)
                            binding.progressBar.isVisible = false
                        }
                    }
                } else {
                    Log.i("Consulta", "No funciona :(")
                }

            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/a55b575a3f3aa949c7ac1640572b7545/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, SuperheroDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}


