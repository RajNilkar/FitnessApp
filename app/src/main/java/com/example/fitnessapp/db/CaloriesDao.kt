package com.example.fitnessapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CaloriesDao {
    @Insert
    suspend fun insertCalories(calories: CaloriesConsumed)

    @Delete
    suspend fun deleteCalories(calories: CaloriesConsumed)

    @Query("SELECT * FROM calories WHERE date = :date")
    suspend fun getCaloriesByDate(date: String): List<CaloriesConsumed>

    @Query("SELECT * FROM calories")
    suspend fun getAllCalories(): List<CaloriesConsumed>

    @Query("SELECT SUM(total_calories) FROM calories WHERE date = :date")
    suspend fun getAllCaloriesByDate(date: String): Double?

    @Query("SELECT SUM(total_calories) FROM calories WHERE strftime('%m', date) = :month AND strftime('%Y', date) = :year")
    suspend fun getTotalCaloriesForMonth(month: String, year: String): Double?
}