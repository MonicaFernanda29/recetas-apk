package com.ejemplo.myrecipesapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ejemplo.myrecipesapp.data.local.DatabaseProvider
import com.ejemplo.myrecipesapp.data.repository.RecetaRepository
import com.ejemplo.myrecipesapp.presentation.navigation.NavigationGraph
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModel
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModelFactory
import com.ejemplo.myrecipesapp.ui.theme.MyRecipesAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val database = DatabaseProvider.provideDatabase(applicationContext)
            val repository = RecetaRepository(database.recetaDao())
            val factory = RecetaViewModelFactory(repository)

            setContent {
                MyRecipesAppTheme {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        val navController = rememberNavController()
                        val recetaViewModel: RecetaViewModel = viewModel(factory = factory)

                        NavigationGraph(
                            navController = navController,
                            viewModel = recetaViewModel
                        )
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "ERROR: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}