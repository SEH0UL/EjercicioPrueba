package com.sehoul.ejercicioprueba.BoardgamesApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sehoul.ejercicioprueba.R

class GamesAdapter (internal var games: List<Game>, private val onItemSelected: (Int) -> Unit) :
    RecyclerView.Adapter<GamesViewHolder>(){
    // COPIAR Y PEGAR DEL ANTERIOR
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GamesViewHolder(view)
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.render(games[position])
        holder.itemView.setOnClickListener{ onItemSelected(position) }
    }


}