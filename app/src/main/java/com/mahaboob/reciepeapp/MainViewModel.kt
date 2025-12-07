package com.mahaboob.reciepeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    init {
        fetchCategories()
    }
    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = receiepeService.getCategories()
                _categorisState.value = _categorisState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _categorisState.value = _categorisState.value.copy(
                    loading = false,
                    error = "Error fetching Categories ${e.message}"
                )
            }
        }

    }
    private val _categorisState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categorisState
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}