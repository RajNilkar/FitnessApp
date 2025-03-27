package com.example.fitnessapp.homepage

import retrofit2.http.GET
import retrofit2.http.Query

interface ExercisesApi {
    @GET("exercises")
    suspend fun getExercises(
        @Query("muscle") muscle: String
    ): List<Exercises>
}