package com.ejemplo.myrecipesapp.presentation.screens.form

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    viewModel: RecetaViewModel,
    recetaId: Int?,
    onRecipeSaved: () -> Unit
) {
    val recetaExistente = viewModel.getRecetaById(recetaId).collectAsState(initial = null).value

    var nombre by remember { mutableStateOf(recetaExistente?.nombre ?: "") }
    var ingredientes by remember { mutableStateOf(recetaExistente?.ingredientes ?: "") }
    var pasos by remember { mutableStateOf(recetaExistente?.pasos ?: "") }
    var categoria by remember { mutableStateOf(recetaExistente?.categoria ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(if (recetaExistente != null) "Editar Receta" else "Nueva Receta")
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = ingredientes,
                onValueChange = { ingredientes = it },
                label = { Text("Ingredientes") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = pasos,
                onValueChange = { pasos = it },
                label = { Text("Pasos") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = categoria,
                onValueChange = { categoria = it },
                label = { Text("Categoría") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (recetaExistente != null) {
                        val actualizada = recetaExistente.copy(
                            nombre = nombre,
                            ingredientes = ingredientes,
                            pasos = pasos,
                            categoria = categoria
                        )
                        viewModel.actualizarReceta(actualizada)
                    } else {
                        viewModel.insertarReceta(nombre, ingredientes, pasos, categoria)
                    }
                    onRecipeSaved()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (recetaExistente != null) "Actualizar" else "Guardar")
            }
        }
    }
}