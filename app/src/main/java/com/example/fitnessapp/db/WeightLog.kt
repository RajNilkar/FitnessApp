package com.example.fitnessapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "weight_log")
data class WeightLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weight: Float,
    val timestamp: Long = System.currentTimeMillis()
)
