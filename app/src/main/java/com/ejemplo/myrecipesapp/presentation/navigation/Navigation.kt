package com.ejemplo.myrecipesapp.presentation.navigation

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ejemplo.myrecipesapp.presentation.screens.detail.DetailScreen
import com.ejemplo.myrecipesapp.presentation.screens.form.FormScreen
import com.ejemplo.myrecipesapp.presentation.screens.home.HomeScreen
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModel

object AppRoutes {
    const val HOME = "home"
    const val FORM = "formulario"
    const val FORM_WITH_PARAM = "formulario?recetaId={recetaId}"
    const val DETAIL = "detalle/{recetaId}"
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: RecetaViewModel) {
    NavHost(navController = navController, startDestination = AppRoutes.HOME) {

        composable(AppRoutes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                onAddClick = { navController.navigate(AppRoutes.FORM) }
            )
        }

        composable(
            route = AppRoutes.FORM_WITH_PARAM,
            arguments = listOf(
                navArgument("recetaId") {
                    type = NavType.IntType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val recetaId = backStackEntry.arguments?.getInt("recetaId")
            FormScreen(
                viewModel = viewModel,
                recetaId = recetaId,
                onRecipeSaved = { navController.popBackStack() }
            )
        }

        composable(
            route = AppRoutes.DETAIL,
            arguments = listOf(
                navArgument("recetaId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val recetaId = backStackEntry.arguments?.getInt("recetaId")
            if (recetaId == null) {
                Log.e("AppNavigation", "recetaId es null")
                Text("Error: recetaId inválido", modifier = Modifier.padding(16.dp))
                return@composable
            }

            val recetas = viewModel.recetas.collectAsState().value
            val receta = recetas.find { it.id == recetaId }

            if (receta != null) {
                DetailScreen(
                    receta = receta,
                    viewModel = viewModel,
                    onEditClick = {
                        navController.navigate("formulario?recetaId=${receta.id}")
                    },
                    onDeleteClick = {
                        viewModel.eliminarReceta(receta)
                        navController.popBackStack()
                    }
                )
            } else {
                Log.e("AppNavigation", "No se encontró la receta con ID: $recetaId")
                Text("Receta no encontrada", modifier = Modifier.padding(16.dp))
            }
        }
    }
}
