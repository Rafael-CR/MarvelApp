package com.rrcr.prueba.data.net

import com.rrcr.prueba.data.model.MainResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {
    @GET("/v1/public/characters")
    suspend fun getData(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: String,
    ): Response<MainResponse>

    @GET("/v1/public/characters/{characterId}/{details}")
    suspend fun getDetailsById(
        @Path("characterId") idCharacter: String,
        @Path("details") details: String,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: String
    ): Response<MainResponse>
}