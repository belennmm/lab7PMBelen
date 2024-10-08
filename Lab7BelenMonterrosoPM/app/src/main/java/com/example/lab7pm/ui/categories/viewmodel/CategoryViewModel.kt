package com.example.lab7pm.ui.categories.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7pm.networking.dataClasses.Category
import com.example.lab7pm.ui.categories.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository = CategoryRepository()) : ViewModel() {

    var categoriesState: MutableState<List<Category>> = mutableStateOf(emptyList())

    init {

        viewModelScope.launch(Dispatchers.IO) {
            val categories = getCategories()
            categoriesState.value = categories
        }
    }


    private suspend fun getCategories(): List<Category> {
        return repository.getCategories().categories
    }
}
