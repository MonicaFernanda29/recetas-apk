package com.ejemplo.myrecipesapp.data.repository

import com.ejemplo.myrecipesapp.data.local.dao.RecetaDao
import com.ejemplo.myrecipesapp.data.local.entity.RecetaEntity
import kotlinx.coroutines.flow.Flow

class RecetaRepository(private val dao: RecetaDao) {

    fun obtenerTodas(): Flow<List<RecetaEntity>> = dao.obtenerTodas()

    suspend fun insertar(receta: RecetaEntity) = dao.insertar(receta)

    suspend fun actualizar(receta: RecetaEntity) = dao.actualizar(receta)

    suspend fun eliminar(receta: RecetaEntity) = dao.eliminar(receta)

    suspend fun obtenerPorId(id: Int): RecetaEntity? = dao.obtenerPorId(id)
    fun getById(id: Int): Flow<RecetaEntity?> {
        return dao.getById(id)
    }
}
