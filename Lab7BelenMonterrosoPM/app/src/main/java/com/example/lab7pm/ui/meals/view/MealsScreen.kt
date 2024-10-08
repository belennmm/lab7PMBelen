package com.example.lab7pm.ui.meals.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lab7pm.Texto
import com.example.lab7pm.TextoDescripcion
import com.example.lab7pm.networking.dataClasses.MealX
import com.example.lab7pm.ui.meals.viewmodel.MealsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsScreen(
    navController: NavHostController,
    categoryId: String?  //  categoryId de la navegación
) {
    val viewModel: MealsViewModel = viewModel()
    val meals = viewModel.mealsState.value  // list

    Column(modifier = Modifier.padding(16.dp)) {
        // para regresar
        TopAppBar(
            title = { Text(text = "Meals") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {  // pantalla inicial
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
        )


        LazyColumn {
            if (!categoryId.isNullOrEmpty() && meals.isNotEmpty()) {
                val filteredMeals = meals.filter { it.strCategory == categoryId }

                if (filteredMeals.isNotEmpty()) {
                    items(filteredMeals) { meal ->
                        MealDetail(meal = meal)
                    }
                } else {

                    item {
                        EmptyMealDetail()  // en caso de no encontrar
                    }
                }
            } else {

                item {
                    EmptyMealDetail()
                }
            }
        }
    }
}

@Composable
fun MealDetail(meal: MealX) {
    Column(modifier = Modifier.padding(8.dp)) {
        Card {
            Column {

                Texto(
                    texto = meal.strMeal ?: "Unknown",
                    fontSize = 18.sp,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )


                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meal.strMealThumb)
                        .crossfade(true)
                        .error(android.R.drawable.stat_notify_error)
                        .build(),
                    contentDescription = "Meal Image",
                    modifier = Modifier.fillMaxWidth()
                )

                TextoDescripcion("Area: " + (meal.strArea ?: "Unknown"))
                TextoDescripcion("Preparation: " + (meal.strInstructions ?: "No instructions available"))


                TextoDescripcion("Ingredients:")
                meal.strIngredient1?.let { TextoDescripcion(it) }
                meal.strIngredient2?.let { TextoDescripcion(it) }
                meal.strIngredient3?.let { TextoDescripcion(it) }
                meal.strIngredient4?.let { TextoDescripcion(it) }
                meal.strIngredient5?.let { TextoDescripcion(it) }
                meal.strIngredient6?.let { TextoDescripcion(it) }
                meal.strIngredient7?.let { TextoDescripcion(it) }
                meal.strIngredient8?.let { TextoDescripcion(it) }
                meal.strIngredient9?.let { TextoDescripcion(it) }
                meal.strIngredient10?.let { TextoDescripcion(it) }
            }
        }
    }
}

@Composable
fun TextoDescripcion(descripcion: String, modifier: Modifier = Modifier) {
    Text(
        text = descripcion,
        fontSize = 16.sp,
        modifier = modifier.padding(4.dp)
    )
}


@Composable
fun EmptyMealDetail() {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // mensaje de receta vacía
                Texto(
                    texto = "No Meals Available",
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 9.dp)
                )


                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("")  // No cargamos ninguna imagen
                        .crossfade(true)
                        .error(android.R.drawable.stat_notify_error)
                        .build(),
                    contentDescription = "No Meal Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                TextoDescripcion("Area: Unknown")
                TextoDescripcion("Preparation: No instructions available")

                TextoDescripcion("Ingredients:")
                TextoDescripcion("None")
            }
        }
    }
}
