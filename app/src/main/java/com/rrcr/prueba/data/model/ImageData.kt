package com.rrcr.prueba.data.model

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("path") val url: String,
    @SerializedName("extension") val extension: String
)