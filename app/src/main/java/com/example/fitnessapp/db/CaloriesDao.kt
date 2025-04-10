package com.example.fitnessapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CaloriesDao {
    @Insert
    suspend fun insertCalories(calories: CaloriesBurned)

    @Delete
    suspend fun  deleteCalories(calories: CaloriesBurned)

    @Query("SELECT * FROM calories_burned WHERE date = :date")
    suspend fun getCaloriesByDate(date: String): List<CaloriesBurned>

    @Query("SELECT * FROM calories_burned")
    suspend fun getAllCalories(): List<CaloriesBurned>

    @Query("SELECT SUM(total_calories) FROM calories_burned WHERE date = :date")
    suspend fun getAllWorkouts(date: String): Double?

    @Query("SELECT SUM(total_calories) FROM calories_burned WHERE strftime('%m', date) = :month AND strftime('%Y', date) = :year")
    suspend fun getTotalCaloriesForMonth(month: String, year: String): Double?
}