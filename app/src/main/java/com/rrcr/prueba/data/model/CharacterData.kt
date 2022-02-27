package com.rrcr.prueba.data.model

import com.google.gson.annotations.SerializedName

data class CharacterData(
    @SerializedName("id") val idCharacter: String,
    @SerializedName("name") val nombre: String,
    @SerializedName("thumbnail") val datosImagen: ImageData,
    @SerializedName("title") val titulo: String,
    @SerializedName("description") val descripcion: String
)