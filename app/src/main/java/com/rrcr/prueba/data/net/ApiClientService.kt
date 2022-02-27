package com.rrcr.prueba.data.net


import com.rrcr.prueba.BuildConfig
import com.rrcr.prueba.utils.Utils
import com.rrcr.prueba.data.model.MainResponse
import com.rrcr.prueba.data.model.CharacterData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ApiClientService @Inject constructor(private val api: ApiClient, private val utils: Utils) {
    private val MARVEL_PRIVATE_KEY = BuildConfig.PRIVATE_KEY
    private val MARVEL_PUBLIC_KEY = BuildConfig.PUBLIC_KEY

    suspend fun getData(offset: String): List<CharacterData> {
        return withContext(Dispatchers.IO) {
            val timestamp = utils.getTimestampString()
            val value = timestamp + MARVEL_PRIVATE_KEY + MARVEL_PUBLIC_KEY
            val hash = utils.getHash(value)
            val response: Response<MainResponse> = api.getData(
                ts = timestamp,
                apikey = MARVEL_PUBLIC_KEY,
                hash = hash,
                offset = offset
            )
            response.body()?.datos?.results ?: emptyList()
        }
    }

    suspend fun getDetailsByCharacterId(
        offset: String,
        characterId: String,
        detail: String
    ): List<CharacterData> {
        return withContext(Dispatchers.IO) {
            val timestamp = utils.getTimestampString()
            val value = timestamp + MARVEL_PRIVATE_KEY + MARVEL_PUBLIC_KEY
            val hash = utils.getHash(value)
            val response: Response<MainResponse> = api.getDetailsById(
                ts = timestamp,
                apikey = MARVEL_PUBLIC_KEY,
                hash = hash,
                offset = offset,
                idCharacter = characterId,
                details = detail
            )
            response.body()?.datos?.results ?: emptyList()
        }
    }

}