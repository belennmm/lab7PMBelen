package com.example.lab7pm.ui.meals.viewmodel

import com.example.lab7pm.networking.dataClasses.MealX


data class MealsState(
    val isLoading: Boolean = false,
    val filter: List<MealX> = emptyList(),
    val error: String = ""

)
