package eu.tutorials.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson

@Composable
fun RecipeApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "recipeScreen") {
        composable("recipeScreen") {
            RecipeScreen(navController = navController)
        }
        val gson = Gson()

        composable(
            route = "detailScreen/{categoryJson}",
            arguments = listOf(
                navArgument("categoryJson") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("categoryJson")
            val category = gson.fromJson(json, Category::class.java)
            MealDetailScreen(category)
        }
    }
}
