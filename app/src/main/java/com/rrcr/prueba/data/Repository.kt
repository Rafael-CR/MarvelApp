package com.rrcr.prueba.data

import android.util.Log
import com.rrcr.prueba.data.database.dao.DataDao
import com.rrcr.prueba.data.database.entities.DataEntity
import com.rrcr.prueba.data.database.entities.toDatabase
import com.rrcr.prueba.data.model.CharacterData
import com.rrcr.prueba.data.model.MainData
import com.rrcr.prueba.data.model.toDomain
import com.rrcr.prueba.data.net.ApiClientService
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ApiClientService,
    private val dataDao: DataDao
) {


    suspend fun getDataFromApi(offset: String): List<MainData> {
        val response: List<CharacterData> = api.getData(offset)
        return response.map { it.toDomain() }
    }

    suspend fun getDetailsById(offset: String, idCharacter: String, details: String): List<CharacterData> {
        return api.getDetailsByCharacterId(offset, idCharacter, details)

    }

    suspend fun getAllFavorites(): List<CharacterData> {
        val response = dataDao.getAllData()
        return response.map { it.toDomain() }
    }

    suspend fun insertFav(data: CharacterData) {
        Log.d("****Favorito", "añadiendo a fav $data")
        dataDao.insertFav(data.toDatabase())
    }


}