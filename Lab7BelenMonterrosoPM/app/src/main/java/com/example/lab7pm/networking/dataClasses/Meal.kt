package com.example.lab7pm.networking.dataClasses

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
) {
    val strInstructions: String?
        get() {
            TODO()
        }
}