package com.example.lab7pm.networking

import com.example.lab7pm.networking.dataClasses.categories
import com.example.lab7pm.networking.dataClasses.mealD
import com.example.lab7pm.networking.dataClasses.mealSea
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MealService {

    private lateinit var api: MealsApi


    init {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(MealsApi::class.java)
    }


    suspend fun getMCategories(): categories {
        return api.getCategories()
    }


    suspend fun getMSea(): mealSea {
        return api.getMealSea()
    }


    suspend fun getMealD(): mealD {
        return api.getMealD()
    }

}