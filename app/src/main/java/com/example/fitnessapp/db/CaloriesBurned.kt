package com.example.fitnessapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calories_burned")
data class CaloriesBurned (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val calories_per_hour: Double,
    val duration_minutes: Int,
    val total_calories: Double,
    val date: String // in the format of yyyy-mm-dd
)