package com.ejemplo.myrecipesapp.data.remote


import com.ejemplo.myrecipesapp.domain.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecetaApiService {
    @GET("search.php")
    suspend fun buscarRecetas(@Query("s") query: String): MealResponse
}