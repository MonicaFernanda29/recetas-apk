package com.ejemplo.myrecipesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ejemplo.myrecipesapp.data.local.dao.RecetaDao
import com.ejemplo.myrecipesapp.data.local.entity.RecetaEntity

@Database(entities = [RecetaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recetaDao(): RecetaDao
}
