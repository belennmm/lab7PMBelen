package com.example.lab7pm.ui.meals.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7pm.networking.dataClasses.MealX
import com.example.lab7pm.ui.meals.repository.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsViewModel(private val repository: MealsRepository = MealsRepository()) : ViewModel() {

    val mealsState: MutableState<List<MealX>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    private suspend fun getMeals(): List<MealX> {
        return repository.getMeals().meals
    }
}
