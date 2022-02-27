package com.rrcr.prueba.ui.view.adapter.generic

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rrcr.prueba.data.model.CharacterData
import com.rrcr.prueba.databinding.ItemGenericDetailBinding
import com.rrcr.prueba.utils.load

class GenericViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGenericDetailBinding.bind(view)

    fun createView(characterModel: CharacterData){
        binding.nameDetail.text = characterModel.titulo
        binding.descriptionDetail.text = characterModel.descripcion
        if(characterModel.datosImagen != null){
            val imagenURL = characterModel.datosImagen.url.replace("http", "https") + "." +characterModel.datosImagen.extension
            binding.imgDetail.load(imagenURL )
        }else{
            binding.imgDetail.visibility = View.GONE
        }
    }
}