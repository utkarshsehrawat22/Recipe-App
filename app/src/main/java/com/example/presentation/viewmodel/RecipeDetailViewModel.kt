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

class RecipeDetailViewModel : ViewModel() {
    private val repository: RecipeRepository = RecipeRepositoryImpl(
        apIservice = RecipeAPIservice(
            KtorClient.client
        )
    )
    
    var isloading by mutableStateOf(false)
        private set
    var errormessage by mutableStateOf<String?>(null)
        private set
    var recipe by mutableStateOf<RecipeDTO?>(null)
        private set

    fun fetchRecipeDetail(id: Int) {
        isloading = true
        errormessage = null
        viewModelScope.launch {
            try {
                recipe = repository.getRecipeByID(id)
            } catch (e: Exception) {
                errormessage = e.message ?: "An unexpected error occurred"
            } finally {
                isloading = false
            }
        }
    }
}
