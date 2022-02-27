package com.rrcr.prueba.ui.view.adapter.fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.R
import com.rrcr.prueba.data.model.CharacterData
import com.rrcr.prueba.data.model.MainData
import com.rrcr.prueba.ui.view.adapter.generic.GenericViewHolder

class FavAdapter(
    private val listCharacteres: List<CharacterData>,
    private val onClickListener: (CharacterData) -> Unit
) : RecyclerView.Adapter<FavViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavViewHolder(layoutInflater.inflate(R.layout.item_fav, parent, false))
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val item = listCharacteres[position]
        holder.createView(item, onClickListener)
    }

    override fun getItemCount(): Int = listCharacteres.size
}