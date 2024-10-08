package com.example.lab7pm.ui.mealsData.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lab7pm.navigation.MainScreen
import com.example.lab7pm.networking.dataClasses.Meal
import com.example.lab7pm.ui.mealsData.viewmodel.MealDataViewModel

@Composable
fun MealsDataView(
    navController: NavHostController,
    name: String?,
    id: String?
) {
    val viewModel: MealDataViewModel = viewModel()
    val meals = viewModel.MealsDataState.value  // estado de  comidas

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = name ?: "Recipes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // si hay o no
        if (meals.isEmpty()) {

            Text(text = "No meals available", modifier = Modifier.padding(16.dp))
        } else {
            // lista de recetas
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(meals) { meal ->
                    MealCard(meal = meal, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MealCard(
    meal: Meal,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // al hacer click ir a
                navController.navigate(MainScreen.MealsScreen.createRoute(meal.idMeal)) {  // Asegúrate de pasar meal.idMeal
                    launchSingleTop = true
                }
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // imagen
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.strMealThumb)
                    .crossfade(true)
                    .error(android.R.drawable.stat_notify_error)
                    .build(),
                contentDescription = "Meal Image",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // instrucciones resumida
            Column {
                Text(
                    text = meal.strMeal,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF8eb5c7)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = meal.strInstructions ?: "No  available",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
