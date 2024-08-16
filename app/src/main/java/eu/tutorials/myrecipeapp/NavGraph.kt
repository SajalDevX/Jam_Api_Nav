package eu.tutorials.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun RecipeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipeScreen") {
        composable("recipeScreen") {
            RecipeScreen(navController = navController)
        }
        composable(
            route = "detailScreen/{idCategory}/{strCategory}/{strCategoryThumb}/{strCategoryDescription}",
            arguments = listOf(
                navArgument("idCategory") { type = NavType.StringType },
                navArgument("strCategory") { type = NavType.StringType },
                navArgument("strCategoryThumb") { type = NavType.StringType },
                navArgument("strCategoryDescription") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val category = Category(
                idCategory = backStackEntry.arguments?.getString("idCategory") ?: "",
                strCategory = backStackEntry.arguments?.getString("strCategory") ?: "",
                strCategoryThumb = backStackEntry.arguments?.getString("strCategoryThumb") ?: "",
                strCategoryDescription = backStackEntry.arguments?.getString("strCategoryDescription") ?: ""
            )
            MealDetailScreen(category)
        }

    }
}
