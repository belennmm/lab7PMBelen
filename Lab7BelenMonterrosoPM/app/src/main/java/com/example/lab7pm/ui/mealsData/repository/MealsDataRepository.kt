package com.example.lab7pm.ui.mealsData.repository


import com.example.lab7pm.networking.MealService
import com.example.lab7pm.networking.MealsApi
import com.example.lab7pm.networking.dataClasses.categories
import com.example.lab7pm.networking.dataClasses.mealD
import com.example.lab7pm.networking.dataClasses.mealSea
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class MealsDataRepository(private val webService: MealService = MealService()) {

    suspend fun getMealsData(): mealSea {
        return webService.getMSea()
    }
}
