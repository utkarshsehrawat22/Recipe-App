package com.example.data.repository

import com.example.data.remote.RecipeAPIservice
import com.example.data.remote.dto.RecipeDTO
import com.example.domain.Repository.RecipeRepository

class RecipeRepositoryImpl(private val apIservice: RecipeAPIservice) : RecipeRepository {
    override suspend fun getAllRecipes(): List<RecipeDTO> {
        return apIservice.getAllRecipes().recipes

    }

    override suspend fun getRecipeByID(id: Int): RecipeDTO {
        return apIservice.getRecipeByID(id)
    }
}