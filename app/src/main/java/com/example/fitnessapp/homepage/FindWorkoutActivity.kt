package com.example.fitnessapp.homepage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

class FindWorkoutActivity : AppCompatActivity() {

    private val viewModel: ExercisesViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var exercisesAdapter: ExercisesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find_workout)

        recyclerView= findViewById(R.id.rclViewExercises)
        recyclerView.layoutManager= LinearLayoutManager(this)

        viewModel.listOfExercises.observe(this){exerciseList ->
            for(exercise in exerciseList)
            {
                println("Exercise: ${exercise.name} (${exercise.muscle})")
            }
            exercisesAdapter = ExercisesAdapter(exerciseList)
            recyclerView.adapter= exercisesAdapter
        }

        viewModel.fetchExercises("chest")
    }
}