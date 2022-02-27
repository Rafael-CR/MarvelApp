package com.rrcr.prueba.ui.view.adapter.character

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.data.model.MainData
import com.rrcr.prueba.databinding.ItemCharacterBinding
import com.rrcr.prueba.utils.load

class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemCharacterBinding.bind(view)

    fun createView(characterModel: MainData, onClickListener:(MainData) -> Unit, onClickFav:(MainData) -> Unit){
        binding.nameCharacter.text = characterModel.nombre
        binding.imgCharacter.load(characterModel.urlImagen)
        itemView.setOnClickListener { onClickListener(characterModel) }
        //binding.favBtn.setOnClickListener { onClickFav(characterModel) }
    }

}
