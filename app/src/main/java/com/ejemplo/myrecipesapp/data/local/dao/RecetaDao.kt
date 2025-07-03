package com.ejemplo.myrecipesapp.data.local.dao

import androidx.room.*
import com.ejemplo.myrecipesapp.data.local.entity.RecetaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecetaDao {

    @Query("SELECT * FROM recetas ORDER BY id DESC")
    fun obtenerTodas(): Flow<List<RecetaEntity>>

    @Query("SELECT * FROM recetas WHERE id = :id")
    suspend fun obtenerPorId(id: Int): RecetaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(receta: RecetaEntity)

    @Update
    suspend fun actualizar(receta: RecetaEntity)

    @Delete
    suspend fun eliminar(receta: RecetaEntity)
    @Query("SELECT * FROM recetas WHERE id = :id")
    fun getById(id: Int): Flow<RecetaEntity?>
}
