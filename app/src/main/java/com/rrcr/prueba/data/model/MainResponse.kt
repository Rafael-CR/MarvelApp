package com.rrcr.prueba.data.model

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("code") val codigo: String,
    @SerializedName("status") val status: String,
    @SerializedName("data") val datos: DataResponse
)