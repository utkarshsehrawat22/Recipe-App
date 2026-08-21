package com.example.data.remote

import com.example.data.remote.dto.RecipeDTO
import com.example.data.remote.dto.RecipeResponse
import com.example.data.remote.dto.addRecipeRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RecipeAPIservice(private val client: HttpClient) {
    suspend fun getAllRecipes(): RecipeResponse{
        return client.get(urlString = "${KtorClient.BASE_URL}/recipes").body()
    }

    suspend fun getRecipeByID(id: Int): RecipeDTO{
        return client.get("${KtorClient.BASE_URL}/recipe/$id").body()
    }

    suspend fun addRecipe(request: addRecipeRequest){
        client.post ("${KtorClient.BASE_URL}/recipes/add"){
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}