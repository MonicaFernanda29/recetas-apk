package com.ejemplo.myrecipesapp.presentation.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ejemplo.myrecipesapp.data.local.entity.RecetaEntity
import com.ejemplo.myrecipesapp.presentation.navigation.Routes
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    viewModel: RecetaViewModel,
    navController: NavHostController,
    onAddClick: () -> Unit
) {
    val recetas = viewModel.recetas.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Recetas") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Routes.REMOTE)
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar en API")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text("+")
            }
        }
    ) { padding ->
        if (recetas.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Text("No hay recetas registradas aún")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(recetas.value) { receta ->
                    RecetaItem(receta = receta, onClick = {
                        val ruta = Routes.DETAIL.replace("{recetaId}", receta.id.toString()) // ✅ navegación correcta
                        Log.d("HomeScreen", "Navegando a $ruta")
                        navController.navigate(ruta)
                    })
                }
            }
        }
    }
}

@Composable
fun RecetaItem(receta: RecetaEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = receta.nombre, style = MaterialTheme.typography.titleMedium)
            Text(text = receta.categoria, style = MaterialTheme.typography.bodySmall)
        }
    }
}