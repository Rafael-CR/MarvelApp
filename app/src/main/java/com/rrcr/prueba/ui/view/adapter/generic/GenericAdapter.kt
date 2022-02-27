package com.rrcr.prueba.ui.view.adapter.generic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.R
import com.rrcr.prueba.data.model.CharacterData

class GenericAdapter(private val listCharacteres: List<CharacterData>): RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GenericViewHolder(layoutInflater.inflate(R.layout.item_generic_detail, parent, false))
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = listCharacteres[position]
        holder.createView(item)
    }

    override fun getItemCount(): Int = listCharacteres.size
}