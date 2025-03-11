package com.example.fitnessapp.homepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.fitnessapp.R


class ExercisesActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        // Get references to the Cards
        val cardLogWorkout = findViewById<CardView>(R.id.cardLogWorkout)
        val cardFindWorkout = findViewById<CardView>(R.id.cardFindWorkout)

        // Navigate to LogWorkoutActivity
        cardLogWorkout.setOnClickListener {
            val intent = Intent(this, LogWorkoutActivity::class.java)
            startActivity(intent)
        }

        // Navigate to FindWorkoutActivity
        cardFindWorkout.setOnClickListener {
            val intent = Intent(this, FindWorkoutActivity::class.java)
            startActivity(intent)
        }
    }
}