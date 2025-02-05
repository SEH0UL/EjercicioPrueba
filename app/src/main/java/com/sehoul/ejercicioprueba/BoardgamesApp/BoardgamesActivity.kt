package com.sehoul.ejercicioprueba.BoardgamesApp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sehoul.ejercicioprueba.BoardgamesApp.GameCategory.*
import com.sehoul.ejercicioprueba.R

class BoardgamesActivity : AppCompatActivity() {
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvGames: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var fabAddGame: FloatingActionButton


    private var categories = listOf(
        Cooperative, Deckbuilding, Euro, LCG, Legacy
    )

    private var games = mutableListOf(
        // String ; Categoria ;
        Game("Ruleta rusa", Euro),
        Game("Poker", LCG),
        Game("Fire & Water", Cooperative),
        Game("Minecraft", Deckbuilding),
        Game("Hogwarts Legacy", Legacy)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boardgames)

        initComponents()
        initUI()
        initListeners()

    }

    private fun initListeners() {
        fabAddGame.setOnClickListener { showDialog() }
    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_game)

        val btnAddGame: Button = dialog.findViewById(R.id.btnAddGame)
        val etGame: EditText = dialog.findViewById(R.id.etGame)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddGame.setOnClickListener {
            // GUARDAMOS EL TEXTO DEL NUEVO JUEGO
            val currentGame = etGame.text.toString()
            // SI EL TEXTO NO ESTA VACIO
            if (currentGame.isNotEmpty()) {
                // ID del radio button que esta marcado
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)

                val currentCategory: GameCategory = when (selectedRadioButton.text) {
                    getString(R.string.dialog_cooperative_category) -> Cooperative
                    getString(R.string.dialog_deckbuilding_category) -> Deckbuilding
                    getString(R.string.dialog_euro_category) -> Euro
                    getString(R.string.dialog_lcg_category) -> LCG
                    else -> Legacy
                }
                games.add(Game(currentGame, currentCategory))
                updateGames()
                dialog.hide()
            }
        }
        dialog.show()
    }

    private fun updateGames(){
        val selectedCategories: List<GameCategory> = categories.filter { it.isSelected }
        val newGames = games.filter { selectedCategories.contains(it.category) }
        gamesAdapter.games = newGames

        gamesAdapter.notifyDataSetChanged()
    }





    private fun initComponents() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvGames = findViewById<RecyclerView>(R.id.rvGames)
        fabAddGame = findViewById(R.id.fabAddGame)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) {position -> onCategorySelected(position)}
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        gamesAdapter = GamesAdapter(games) { position -> onGameSelected(position)}
        rvGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGames.adapter = gamesAdapter
    }

    private fun onGameSelected(position:Int){
        games[position].isSelected = !games[position].isSelected
        updateGames()
    }

    private fun onCategorySelected(position:Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateGames()
    }


}