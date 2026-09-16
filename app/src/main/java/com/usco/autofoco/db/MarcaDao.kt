package com.usco.autofoco.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarcaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTodas(marcas: List<MarcaEntity>)

    @Query("SELECT * FROM marcas ORDER BY makeName ASC")
    suspend fun obtenerTodas(): List<MarcaEntity>
}
