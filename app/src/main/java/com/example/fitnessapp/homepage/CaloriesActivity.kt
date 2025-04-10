package com.example.fitnessapp.homepage

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fitnessapp.R
import com.example.fitnessapp.db.AppDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CaloriesActivity: AppCompatActivity() {
    private lateinit var tvCaloriesToday: TextView
    private lateinit var tvCaloriesMonth: TextView
    private lateinit var progressBarCalories: ProgressBar

    private val dailyGoal = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories)

        tvCaloriesToday = findViewById<TextView>(R.id.txtCaloriesToday)
        tvCaloriesMonth = findViewById<TextView>(R.id.txtCaloriesMonth)
        progressBarCalories = findViewById(R.id.progressBarCalories)

        val workoutDao = AppDatabase.getDatabase(this).workoutDao()
        val caloriesDao = AppDatabase.getDatabase(this).caloriesDoa()

        lifecycleScope.launch {
            val allCalories = caloriesDao.getAllCalories()

            val today = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).format(Date())
            val currentMonth = today.substring(0, 7) // yyyy-MM

            val caloriesToday = allCalories.filter { it.date == today }
                .sumOf { it.total_calories }

            val caloriesThisMonth = allCalories.filter { it.date.startsWith(currentMonth) }
                .sumOf { it.total_calories }

            tvCaloriesToday.text = "${caloriesToday.toInt()} kcal"
            tvCaloriesMonth.text = "${caloriesThisMonth.toInt()} kcal"

            //update progress bar
            progressBarCalories.max = dailyGoal
            progressBarCalories.progress = caloriesToday.toInt().coerceAtMost(dailyGoal)
        }
    }
}