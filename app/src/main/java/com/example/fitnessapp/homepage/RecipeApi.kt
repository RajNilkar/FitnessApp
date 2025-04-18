package com.example.fitnessapp.homepage

import com.example.fitnessapp.db.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeApi {
    @GET("api/recipes/v2")
    suspend fun getRecipes(
        @Header("Edamam-Account-User") user: String,
        @Query("type") type: String = "public",
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String
    ): RecipeResponse
}