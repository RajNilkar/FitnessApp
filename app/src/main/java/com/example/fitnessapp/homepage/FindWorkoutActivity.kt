package com.example.fitnessapp.homepage

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import kotlinx.coroutines.launch

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

        val spinner= findViewById<Spinner>(R.id.spinnerMuscleGroups)
        val muscleGroups= listOf(
            "Select Muscle Group",
            "abdominals", "biceps", "calves", "chest", "forearms", "glutes", "hamstrings", "lats",
            "lower_back", "upper_back", "quadriceps", "traps", "triceps"
        )

        val spinnerAdapter= ArrayAdapter(this, R.layout.item_spinner, muscleGroups)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter= spinnerAdapter

        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedMuscle= muscleGroups[position]
                if(selectedMuscle != "Select Muscle Group"){
                    viewModel.fetchExercises(selectedMuscle)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Nothing is selected
            }
        }

        viewModel.listOfExercises.observe(this) { exerciseList ->
            val db = com.example.fitnessapp.db.AppDatabase.getDatabase(this)
            val workoutDao = db.workoutDao()

            exercisesAdapter = ExercisesAdapter(exerciseList) { workout ->
                lifecycleScope.launch {
                    workoutDao.insertWorkout(workout)
                    Toast.makeText(this@FindWorkoutActivity, "${workout.name} added to log!", Toast.LENGTH_SHORT).show()
                }
            }
            recyclerView.adapter= exercisesAdapter
        }

    }
}