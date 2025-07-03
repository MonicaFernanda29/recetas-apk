package com.ejemplo.myrecipesapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ejemplo.myrecipesapp.data.repository.RecetaRepository

class RecetaViewModelFactory(private val repository: RecetaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecetaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecetaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
