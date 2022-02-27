package com.rrcr.prueba.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable
import com.rrcr.prueba.data.database.dao.DataDao
import com.rrcr.prueba.data.database.entities.DataEntity

@Database(entities = [DataEntity::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract fun getDataDao():DataDao
}