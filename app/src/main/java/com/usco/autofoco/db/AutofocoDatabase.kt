package com.usco.autofoco.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MarcaEntity::class], version = 1)
abstract class AutofocoDatabase : RoomDatabase() {
    abstract fun marcaDao(): MarcaDao

    companion object {
        @Volatile private var instancia: AutofocoDatabase? = null

        fun obtenerInstancia(context: Context): AutofocoDatabase {
            return instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    AutofocoDatabase::class.java,
                    "autofoco_db"
                ).build().also { instancia = it }
            }
        }
    }
}
