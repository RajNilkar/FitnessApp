package com.example.fitnessapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calories")
data class CaloriesConsumed(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val foodName: String,
    val imageUrl: String,
    val total_calories: Double,
    val protein: Double,
    val carbs: Double,
    val fat: Double,
    val sodium: Double,
    val date: String
)