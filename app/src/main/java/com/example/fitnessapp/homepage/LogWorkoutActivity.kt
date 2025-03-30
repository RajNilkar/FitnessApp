package com.example.fitnessapp.homepage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

class LogWorkoutActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private val workouts = mutableListOf<Workout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_workout)

        recyclerView = findViewById(R.id.recyclerViewWorkouts)
        workoutAdapter = WorkoutAdapter(workouts) { workouts -> deleteWorkout(workouts)}

        recyclerView.adapter = workoutAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val workoutNameEditText = findViewById<EditText>(R.id.etWorkoutName)
        val setsEditText = findViewById<EditText>(R.id.etWorkoutSets)
        val repsEditText = findViewById<EditText>(R.id.etWorkoutReps)
        val addButton = findViewById<Button>(R.id.btnAddWorkout)

        addButton.setOnClickListener {
            val workoutName = workoutNameEditText.text.toString().trim()
            val sets = setsEditText.text.toString().toIntOrNull() ?: 0
            val reps = repsEditText.text.toString().toIntOrNull() ?: 0

            if (workoutName.isNotEmpty() && sets > 0 && reps > 0) {
                val newWorkout = Workout(workoutName, sets, reps)
                workouts.add(newWorkout)
                workoutAdapter.notifyDataSetChanged()

                // Clear input fields
                workoutNameEditText.text.clear()
                setsEditText.text.clear()
                repsEditText.text.clear()
            } else {
                Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteWorkout(workout: Workout) {
        workouts.remove(workout)
        workoutAdapter.notifyDataSetChanged()
    }
}