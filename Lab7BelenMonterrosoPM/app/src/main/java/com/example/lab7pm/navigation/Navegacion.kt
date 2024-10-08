package com.example.lab7pm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lab7pm.ui.categories.view.MainCategoryView
import com.example.lab7pm.ui.meals.view.MealsScreen
import com.example.lab7pm.ui.mealsData.view.MealsDataView

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.MainCategoryView.route
    ) {
        // ruta  mainCategoryView
        composable(
            route = MainScreen.MainCategoryView.route
        ) {
            MainCategoryView(navController = navController)
        }

        //  para mealsScreen pasar categoryId
        composable(
            route = MainScreen.MealsScreen.route,
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            MealsScreen(navController = navController, categoryId = categoryId)
        }

        //  para mealsdataView
        composable(
            route = MainScreen.MealsDataView.route,
            arguments = listOf(
                navArgument("categoryName") { type = NavType.StringType },
                navArgument("categoryId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            MealsDataView(navController = navController, name = categoryName, id = categoryId)
        }
    }
}
