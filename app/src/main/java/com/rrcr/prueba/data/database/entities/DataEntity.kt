package com.rrcr.prueba.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rrcr.prueba.data.model.CharacterData
import com.rrcr.prueba.data.model.ImageData
import com.rrcr.prueba.data.model.MainData

@Entity(tableName = "data_table")
data class DataEntity(
    @ColumnInfo(name = "id") val id: Int = 0,
    @PrimaryKey
    @ColumnInfo(name = "id_character") val idCharacter: String,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "imagen") val urlImagen: String,
    @ColumnInfo(name = "extension_imagen") val extension: String
)

fun CharacterData.toDatabase() = DataEntity(
    idCharacter = idCharacter,
    nombre = nombre,
    urlImagen = datosImagen.url,
    extension = datosImagen.extension
)

fun DataEntity.toDomain() = CharacterData(
    idCharacter = idCharacter,
    nombre = nombre,
    datosImagen = ImageData(url = urlImagen, extension = extension),
    titulo = "",
    descripcion = ""
)