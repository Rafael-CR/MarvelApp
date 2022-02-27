package com.rrcr.prueba.data.model

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("offset") val offset: String,
    @SerializedName("limit") val limit: String,
    @SerializedName("total") val total: String,
    @SerializedName("count") val count: String,
    @SerializedName("results") val results: List<CharacterData>
)