package com.rrcr.prueba.ui.view.adapter.fav

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.data.model.CharacterData
import com.rrcr.prueba.data.model.MainData
import com.rrcr.prueba.databinding.ItemCharacterBinding
import com.rrcr.prueba.databinding.ItemFavBinding
import com.rrcr.prueba.utils.load

class FavViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemFavBinding.bind(view)


    fun createView(characterModel: CharacterData, onClickListener:(CharacterData) -> Unit){
        binding.nameCharacter.text = characterModel.nombre
        val imagen = characterModel.datosImagen.url + "." + characterModel.datosImagen.extension
        binding.imgCharacter.load(imagen)
        itemView.setOnClickListener { onClickListener(characterModel) }

    }
}