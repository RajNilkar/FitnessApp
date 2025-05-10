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


        val cardLogWorkout = findViewById<CardView>(R.id.cardLogWorkout)
        val cardFindWorkout = findViewById<CardView>(R.id.cardFindWorkout)

        cardLogWorkout.setOnClickListener {
            val intent = Intent(this, LogWorkoutActivity::class.java)
            startActivity(intent)
        }


        cardFindWorkout.setOnClickListener {
            val intent = Intent(this, FindWorkoutActivity::class.java)
            startActivity(intent)
        }
    }
}