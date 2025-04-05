package com.example.fitnessapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//default sets and reps are set to 3 sets and 10 reps but can be modified
@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    var sets: Int = 3,
    var reps: Int = 10
)