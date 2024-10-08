package com.example.lab7pm.ui.categories.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lab7pm.ui.categories.viewmodel.CategoryViewModel
import com.example.lab7pm.navigation.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCategoryView(navController: NavHostController) {
    val viewModel: CategoryViewModel = viewModel()
    val categories = viewModel.categoriesState.value

    Column {


        TopAppBar(
            title = {
                Text(
                    text = "Categories",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,  // negrita
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
        )

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            // verificar lista
            categories?.let {
                items(categories) { category ->
                    val str: String = category.strCategory
                    val id: String = category.idCategory
                    val description: String = category.strCategoryDescription
                    val thumb: String = category.strCategoryThumb

                    // la card para cada categoría
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .clickable {
                                // pasar categoryId para mealsscreen
                                navController.navigate(MainScreen.MealsScreen.createRoute(id)) {
                                    launchSingleTop = true
                                }
                            },
                        shape = RoundedCornerShape(6.dp),
                        colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFbed5e0)
                        )
                    ) {
                        Row {
                            // imagen
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(thumb)
                                    .crossfade(true)
                                    .size(499, 499)
                                    .build(),
                                contentDescription = "Category Image"
                            )
                            Column(modifier = Modifier.padding(6.dp)) {
                                Text(
                                    text = str,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2c7597), //0xFF2c7597
                                    lineHeight = 20.sp,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = description,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black,
                                    lineHeight = 13.sp,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
