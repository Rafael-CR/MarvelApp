package com.rrcr.prueba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rrcr.prueba.data.database.entities.DataEntity

@Dao
interface DataDao {
    @Query("SELECT * FROM data_table ORDER BY id DESC")
    suspend fun getAllData():List<DataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(data: DataEntity)


}