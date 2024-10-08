package com.example.lab7pm.ui.categories.repository


import com.example.lab7pm.networking.MealService
import com.example.lab7pm.networking.MealsApi
import com.example.lab7pm.networking.dataClasses.categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepository(private val webService: MealService = MealService()) {

    suspend fun getCategories(): categories {
        return webService.getMCategories()
    }
}
