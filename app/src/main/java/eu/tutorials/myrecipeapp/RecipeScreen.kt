package eu.tutorials.myrecipeapp

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val recipeViewModel: MainViewModel = viewModel()
    val uiState by recipeViewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            uiState.error != null -> {
                Text("ERROR OCCURRED")
            }
            else -> {
                CategoryScreen(navController = navController, categories = uiState.list)
            }
        }
    }
}

@Composable
fun CategoryScreen(navController: NavHostController, categories: List<Category>) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) { category ->
            CategoryItem(navController = navController, category = category)
        }
    }
}

@Composable
fun CategoryItem(navController: NavHostController, category: Category) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable {
                val encodedDescription = Uri.encode(category.strCategoryDescription)
                val encodedCategory = Uri.encode(category.strCategory)
                val encodedThumb = Uri.encode(category.strCategoryThumb)
                navController.navigate(
                    "detailScreen/${category.idCategory}/$encodedCategory/$encodedThumb/$encodedDescription"
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )

        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
