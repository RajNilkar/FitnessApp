package com.example.fitnessapp.db

data class Recipe(
    val label: String,
    val image: String,
    val source: String,
    val url: String,
    val calories: Double,
    val dietLabels: List<String>,
    val healthLabels: List<String>,
    val cuisineType: List<String>
)