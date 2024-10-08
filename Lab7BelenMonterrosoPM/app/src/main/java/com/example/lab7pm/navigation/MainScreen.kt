package com.example.lab7pm.navigation

sealed class MainScreen(val route: String) {

    //  ruta  maincategoryview
    object MainCategoryView : MainScreen("main_category_view")

    // ruta  mealsscreen y categoryId
    object MealsScreen : MainScreen("meals_screen/{categoryId}") {
        fun createRoute(categoryId: String): String {
            return "meals_screen/$categoryId"
        }
    }

    // ruta  mealsdataview
    object MealsDataView : MainScreen("mealsDataView/{categoryName}/{categoryId}") {
        fun createRoute(categoryName: String, categoryId: String): String {
            return "mealsDataView/$categoryName/$categoryId"
        }
    }
}
