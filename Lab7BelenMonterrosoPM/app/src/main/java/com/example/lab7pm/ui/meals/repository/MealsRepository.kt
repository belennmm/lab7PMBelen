package com.example.lab7pm.ui.meals.repository

import com.example.lab7pm.networking.MealService
import com.example.lab7pm.networking.dataClasses.mealD

class MealsRepository(private val webService: MealService = MealService()) {


    suspend fun getMeals(): mealD {
        return webService.getMealD()
    }
}
