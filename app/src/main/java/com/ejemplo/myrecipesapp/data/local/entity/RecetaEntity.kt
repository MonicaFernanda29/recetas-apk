package com.ejemplo.myrecipesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recetas")
data class RecetaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val ingredientes: String,
    val pasos: String,
    val categoria: String
) : Serializable
