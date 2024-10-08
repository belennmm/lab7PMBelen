package com.example.lab7pm.networking

import com.example.lab7pm.networking.dataClasses.categories
import com.example.lab7pm.networking.dataClasses.mealD
import com.example.lab7pm.networking.dataClasses.mealSea
import retrofit2.http.GET

interface MealsApi {

    @GET("categories.php")
    suspend fun getCategories(): categories


    @GET("filter.php?c=Seafood")
    suspend fun getMealSea(): mealSea


    @GET("lookup.php?i=52944")
    suspend fun getMealD(): mealD
}