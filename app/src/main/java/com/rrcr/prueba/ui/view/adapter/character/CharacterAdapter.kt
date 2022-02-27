package com.rrcr.prueba.ui.view.adapter.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.R
import com.rrcr.prueba.data.model.MainData

class CharacterAdapter(
    private val listCharacteres: List<MainData>,
    private val onClickListener: (MainData) -> Unit,
    private val onClickFav: (MainData) -> Unit,
) :
    RecyclerView.Adapter<CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = listCharacteres[position]
        holder.createView(item, onClickListener, onClickFav)
    }

    override fun getItemCount(): Int = listCharacteres.size
}