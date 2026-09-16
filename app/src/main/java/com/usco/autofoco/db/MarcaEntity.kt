package com.usco.autofoco.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marcas")
data class MarcaEntity(
    @PrimaryKey val makeId: Int,
    val makeName: String
)
