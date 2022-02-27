package com.rrcr.prueba.data.model

import com.rrcr.prueba.data.database.entities.DataEntity
import com.rrcr.prueba.data.model.CharacterData

data class MainData(val id: String, val nombre: String, val urlImagen: String)

fun CharacterData.toDomain() =
    MainData(
        id = idCharacter,
        nombre = nombre,
        urlImagen = datosImagen.url.replace("http", "https") + "." + datosImagen.extension
    )

fun DataEntity.toDomain() = CharacterData(
    idCharacter = idCharacter,
    nombre = nombre,
    datosImagen = ImageData(url = urlImagen, extension = extension),
    titulo = "",
    descripcion = ""
)

fun MainData.toDataBase() = DataEntity(
    idCharacter = id,
    nombre = nombre,
    urlImagen = urlImagen.substring(0, urlImagen.length - 4),
    extension = urlImagen.substring(urlImagen.length - 3, urlImagen.length)
)

fun MainData.toCharacterData() = CharacterData(
    idCharacter = id,
    nombre = nombre,
    datosImagen = ImageData(
        url = urlImagen.substring(0, urlImagen.length - 4),
        extension = urlImagen.substring(urlImagen.length - 3, urlImagen.length)
    ),
    titulo = "",
    descripcion = ""
)