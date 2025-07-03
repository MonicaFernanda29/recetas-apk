package com.ejemplo.myrecipesapp.presentation.navigation

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
import com.ejemplo.myrecipesapp.presentation.screens.detail.RemoteScreen
import com.ejemplo.myrecipesapp.presentation.screens.form.FormScreen
import com.ejemplo.myrecipesapp.presentation.screens.home.HomeScreen
import com.ejemplo.myrecipesapp.presentation.viewmodel.RecetaViewModel

object Routes {
    const val HOME = "home"
    const val FORM = "form"
    const val FORM_WITH_ARG = "form/{recetaId}"
    const val DETAIL = "detalle/{recetaId}"
    const val REMOTE = "remote"

}

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: RecetaViewModel) {
    NavHost(navController = navController, startDestination = Routes.HOME) {

        // HOME
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                onAddClick = {
                    navController.navigate(Routes.FORM)
                }
            )
        }

        // FORM NUEVA
        composable(Routes.FORM) {
            FormScreen(viewModel = viewModel, recetaId = null, onRecipeSaved = {
                navController.popBackStack()
            })
        }

        // FORM EDITAR
        composable(Routes.FORM_WITH_ARG,
            arguments = listOf(navArgument("recetaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recetaId = backStackEntry.arguments?.getInt("recetaId")
            FormScreen(viewModel = viewModel, recetaId = recetaId, onRecipeSaved = {
                navController.popBackStack()
            })
        }

        // DETALLE
        composable(Routes.DETAIL,
            arguments = listOf(navArgument("recetaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recetaId = backStackEntry.arguments?.getInt("recetaId")
            val receta = viewModel.recetas.collectAsState().value.find { it.id == recetaId }

            if (receta != null) {
                DetailScreen(
                    receta = receta,
                    viewModel = viewModel,
                    onEditClick = {
                        navController.navigate("form/${receta.id}")
                    },
                    onDeleteClick = {
                        viewModel.eliminarReceta(receta)
                        navController.popBackStack()
                    }
                )
            } else {
                Text("Receta no encontrada", modifier = Modifier.padding(16.dp))
            }
        }
        // REMOTO - API externa
        composable(Routes.REMOTE) {
            RemoteScreen(viewModel = viewModel)
        }
    }
}