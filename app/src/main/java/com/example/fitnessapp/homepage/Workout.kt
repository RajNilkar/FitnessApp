package com.example.fitnessapp.homepage

//default sets and reps are set to 3 sets and 10 reps but can be modified
data class Workout(
    val name: String,
    var sets: Int = 3,
    var reps: Int = 10
)