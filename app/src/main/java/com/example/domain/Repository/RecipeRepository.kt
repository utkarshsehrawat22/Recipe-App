package com.example.domain.Repository

import com.example.data.remote.dto.RecipeDTO

interface RecipeRepository {
    suspend fun getAllRecipes(): List< RecipeDTO>
    suspend fun getRecipeByID(id: Int): RecipeDTO

}