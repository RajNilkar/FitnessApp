package com.example.fitnessapp.homepage

import com.example.fitnessapp.db.CaloriesBurned
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CaloriesApi {
    @GET("caloriesburned")
    suspend fun getCalories(
        @Header("X-Api-Key") apiKey: String,
        @Query("activity") activity: String,
        @Query("duration") duration: Int
    ): List<CaloriesBurned>
}