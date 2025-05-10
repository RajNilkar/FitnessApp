package com.example.fitnessapp.homepage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.db.AppDatabase
import com.example.fitnessapp.db.Workout
import com.example.fitnessapp.db.WorkoutDao
import kotlinx.coroutines.launch

class LogWorkoutActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private val workouts = mutableListOf<Workout>()
    private lateinit var workoutDao: WorkoutDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_workout)

        val db = AppDatabase.getDatabase(this)
        workoutDao = db.workoutDao()

        recyclerView = findViewById(R.id.recyclerViewWorkouts)
        workoutAdapter = WorkoutAdapter(workouts) { workoutToDelete -> deleteWorkout(workoutToDelete)}

        recyclerView.adapter = workoutAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val workoutNameEditText = findViewById<EditText>(R.id.etWorkoutName)
        val setsEditText = findViewById<EditText>(R.id.etWorkoutSets)
        val repsEditText = findViewById<EditText>(R.id.etWorkoutReps)
        val addButton = findViewById<Button>(R.id.btnAddWorkout)

        lifecycleScope.launch {
            val existingWorkouts = workoutDao.getAllWorkouts()
            workouts.addAll(existingWorkouts)
            workoutAdapter.notifyDataSetChanged()
        }

        addButton.setOnClickListener {
            val workoutName = workoutNameEditText.text.toString().trim()
            val sets = setsEditText.text.toString().toIntOrNull() ?: 0
            val reps = repsEditText.text.toString().toIntOrNull() ?: 0

            if (workoutName.isNotEmpty() && sets > 0 && reps > 0) {
                val newWorkout = Workout(name = workoutName, sets = sets, reps = reps)
                lifecycleScope.launch {
                    workoutDao.insertWorkout(newWorkout)
                    workouts.add(newWorkout)
                    workoutAdapter.notifyDataSetChanged()
                }

                workoutNameEditText.text.clear()
                setsEditText.text.clear()
                repsEditText.text.clear()
            } else {
                Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteWorkout(workout: Workout) {
        lifecycleScope.launch {
            workoutDao.deleteWorkout(workout)
            workouts.remove(workout)
            workoutAdapter.notifyDataSetChanged()
        }
    }
}