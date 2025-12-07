package com.mahaboob.reciepeapp
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun RecipeApp(navController: NavHostController, modifier: Modifier){
    val recipeViewModel: MainViewModel = viewModel()
    //val categoriesState = MutableStateFlow(MainViewModel.RecipeState())

   // val viewstate by recipeViewModel.categoriesState
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.Screen.RecipeScreen.route){
        composable(route = Screen.Screen.RecipeScreen.route){
            RecipeScreen(viewstate = viewstate,
                navigateToDetail = { it
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                    navController.navigate(Screen.Screen.DetailScreen.route)
            })
        }
        composable(Screen.Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat") ?: Category(idCategory = "", strCategory = "", strCategoryThumb = "", strCategoryDescription = "")
            CategoryDetailScreen(category = category)
        }
    }
}
