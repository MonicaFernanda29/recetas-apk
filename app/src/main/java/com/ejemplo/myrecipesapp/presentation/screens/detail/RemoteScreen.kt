package com.ejemplo.myrecipesapp.presentation.screens.detail


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RemoteScreen(viewModel: RecetaViewModel) {
    var query by remember { mutableStateOf("") }
    val recetasRemotas = viewModel.recetasApi.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Buscar Recetas Online") }) }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)) {

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Buscar receta") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (query.isNotBlank()) {
                        viewModel.buscarRecetasRemotas(query)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(recetasRemotas.value) { receta ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = receta.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(text = receta.categoria, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}