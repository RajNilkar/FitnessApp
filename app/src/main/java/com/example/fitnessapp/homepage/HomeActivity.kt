package com.example.fitnessapp.homepage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.fitnessapp.R

class HomeActivity : AppCompatActivity() {
    private lateinit var exercisesCard: CardView
    private lateinit var caloriesCard: CardView
    private lateinit var graphCard: CardView
    private lateinit var myProgressCard: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home) // This will be created in the next step

        val exercisesCard= findViewById<CardView>(R.id.cardExercises)
        val caloriesCard= findViewById<CardView>(R.id.cardCalories)
        val graphCard= findViewById<CardView>(R.id.cardGraphs)
        val myProgressCard= findViewById<CardView>(R.id.cardProgress)

        exercisesCard.setOnClickListener {
            val intent= Intent(this, ExercisesActivity::class.java)
            startActivity(intent)
        }

        caloriesCard.setOnClickListener {
            val intent= Intent(this, CaloriesActivity::class.java)
            startActivity(intent)
        }

        graphCard.setOnClickListener {
            val intent= Intent(this, GraphsActivity::class.java)
            startActivity(intent)
        }

        myProgressCard.setOnClickListener {
            val intent= Intent(this, MyProgressActivity::class.java)
            startActivity(intent)
        }
    }
}
