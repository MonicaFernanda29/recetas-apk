package com.ejemplo.myrecipesapp.presentation.screens.detail


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ejemplo.myrecipesapp.data.local.entity.RecetaEntity
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    receta: RecetaEntity,
    viewModel: RecetaViewModel,
    onEditClick: (RecetaEntity) -> Unit,
    onDeleteClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle de Receta") })
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)) {
            Text("Nombre: ${receta.nombre}", style = MaterialTheme.typography.titleLarge)
            Text("Categoría: ${receta.categoria}", style = MaterialTheme.typography.bodyMedium)
            Text("Ingredientes:\n${receta.ingredientes}", style = MaterialTheme.typography.bodyMedium)
            Text("Pasos:\n${receta.pasos}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = { onEditClick(receta) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Editar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        viewModel.eliminarReceta(receta)
                        onDeleteClick()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Eliminar")
                }
            }
        }

    }

}