package com.example.lab7pm.ui.categories.viewmodel

import com.example.lab7pm.networking.dataClasses.categories

data class CategoryState(
    val isLoading: Boolean = false,
    val categories: List<categories> = emptyList(),
    val error: String = ""
)