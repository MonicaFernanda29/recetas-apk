package com.ejemplo.myrecipesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ejemplo.myrecipesapp.data.local.entity.RecetaEntity
import com.ejemplo.myrecipesapp.data.remote.RetrofitClient
import com.ejemplo.myrecipesapp.data.repository.RecetaRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecetaViewModel(private val repository: RecetaRepository) : ViewModel() {

    private val _recetas = MutableStateFlow<List<RecetaEntity>>(emptyList())
    val recetas: StateFlow<List<RecetaEntity>> = _recetas.asStateFlow()

    private val _recetasApi = MutableStateFlow<List<RecetaEntity>>(emptyList())
    val recetasApi: StateFlow<List<RecetaEntity>> = _recetasApi.asStateFlow()

    init {
        cargarRecetas()
    }

    private fun cargarRecetas() {
        viewModelScope.launch {
            repository.obtenerTodas().collect { lista ->
                _recetas.value = lista
            }
        }
    }

    fun insertarReceta(nombre: String, ingredientes: String, pasos: String, categoria: String) {
        viewModelScope.launch {
            val receta = RecetaEntity(
                nombre = nombre,
                ingredientes = ingredientes,
                pasos = pasos,
                categoria = categoria
            )
            repository.insertar(receta)
            cargarRecetas()
        }
    }

    fun guardarReceta(receta: RecetaEntity) {
        viewModelScope.launch {
            repository.insertar(receta)
        }
    }

    fun eliminarReceta(receta: RecetaEntity) {
        viewModelScope.launch {
            repository.eliminar(receta)
            cargarRecetas()
        }
    }

    fun actualizarReceta(receta: RecetaEntity) {
        viewModelScope.launch {
            repository.actualizar(receta)
            cargarRecetas()
        }
    }

    fun getRecetaById(id: Int?): Flow<RecetaEntity?> {
        return if (id == null) flowOf(null)
        else repository.getById(id)
    }

    fun buscarRecetasRemotas(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.buscarRecetas(query)
                val recetasRemotas = response.meals ?: emptyList()
                Log.d("ViewModel", "Recetas API: ${recetasRemotas.size}")

                // Mapear resultados de API a tu entidad local (si quieres mantener el mismo modelo)
                _recetasApi.value = recetasRemotas.map {
                    RecetaEntity(
                        nombre = it.strMeal,
                        ingredientes = "Ingredientes no disponibles",
                        pasos = it.strInstructions ?: "Sin instrucciones",
                        categoria = it.strCategory ?: "Sin categoría"
                    )
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Error al buscar recetas remotas", e)
            }
        }
    }

}