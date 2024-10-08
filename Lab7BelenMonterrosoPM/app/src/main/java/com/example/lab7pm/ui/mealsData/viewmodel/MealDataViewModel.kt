package com.example.lab7pm.ui.mealsData.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7pm.networking.dataClasses.Category
import com.example.lab7pm.networking.dataClasses.Meal
import com.example.lab7pm.ui.mealsData.repository.MealsDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealDataViewModel(private val repository: MealsDataRepository = MealsDataRepository()):ViewModel(){
    val MealsDataState: MutableState<List<Meal>> = mutableStateOf(emptyList<Meal>())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val mealsDetails = getFilters()
            MealsDataState.value = mealsDetails

        }

    }

    private suspend fun getFilters(): List<Meal> {
        return repository.getMealsData().meals
    }

}
