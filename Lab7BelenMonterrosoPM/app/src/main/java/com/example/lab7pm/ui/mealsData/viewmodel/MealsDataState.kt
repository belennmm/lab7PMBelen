package com.example.lab7pm.ui.mealsData.viewmodel

import com.example.lab7pm.networking.dataClasses.Meal


data class MealsDataState(
    val isLoading: Boolean = false,
    val filter: List<Meal> = emptyList(),
    val error: String = ""

)
