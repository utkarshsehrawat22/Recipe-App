package com.example.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.remote.KtorClient
import com.example.data.remote.RecipeAPIservice
import com.example.data.remote.dto.RecipeDTO
import com.example.data.repository.RecipeRepositoryImpl
import com.example.domain.Repository.RecipeRepository
import kotlinx.coroutines.launch

class homeViewModel() : ViewModel() {
    private val repository: RecipeRepository = RecipeRepositoryImpl(
        apIservice = RecipeAPIservice(
            KtorClient.client
        )
    )

    var loading by mutableStateOf(false)
        private set
    var errormessaging by mutableStateOf<String?>(null)

    var recipes by mutableStateOf<List<RecipeDTO>>(emptyList())
        private set

    var categories by mutableStateOf<List<String>>(listOf("All"))
        private set

    var selectedcategories by mutableStateOf("All")
        private set
    private var allRecipe: List<RecipeDTO> = emptyList()
    init {
        fetchRecipe()
    }

    fun fetchRecipe() {

        loading = true
        errormessaging = null
        viewModelScope.launch {
            try {
                val result = repository.getAllRecipes()
                allRecipe = result
                val cuisine = result.map { it.cuisine }.distinct().sorted()
                categories = listOf("All") + cuisine
                applyfilter()
            } catch (e: Exception) {
                errormessaging = e.message ?: "An Unexpected Error Occurred"
            } finally {
                loading = false
            }

        }
    }

    fun oncategorySelected(category: String) {
        selectedcategories = category
        applyfilter()
    }

    private fun applyfilter() {

        recipes =
            if (selectedcategories == "All") allRecipe
            else allRecipe.filter { it.cuisine == selectedcategories }
    }
}