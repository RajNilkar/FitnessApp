package com.example.fitnessapp.homepage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.db.AppDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CaloriesTrackerFixedActivity : AppCompatActivity() {
    private lateinit var tvCaloriesToday: TextView
    private lateinit var tvCaloriesMonth: TextView
    private lateinit var tvProteinToday: TextView
    private lateinit var tvCarbsToday: TextView
    private lateinit var tvFatToday: TextView
    private lateinit var tvSodiumToday: TextView
    private lateinit var progressBarCalories: ProgressBar
    private lateinit var btnLogMeal: Button
    private lateinit var etCalorieGoal: EditText
    private lateinit var btnSaveGoal: Button
    private lateinit var rvLoggedMeals: RecyclerView

    private lateinit var loggedMealsAdapter: LoggedMealsAdapter
    private var dailyGoal = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calories_tracker)

        tvCaloriesToday = findViewById(R.id.txtCaloriesToday)
        tvCaloriesMonth = findViewById(R.id.txtCaloriesMonth)
        tvProteinToday = findViewById(R.id.txtProteinToday)
        tvCarbsToday = findViewById(R.id.txtCarbsToday)
        tvFatToday = findViewById(R.id.txtFatToday)
        tvSodiumToday = findViewById(R.id.txtSodiumToday)
        progressBarCalories = findViewById(R.id.progressBarCalories)
        btnLogMeal = findViewById(R.id.btnLogMeal)
        etCalorieGoal = findViewById(R.id.etCalorieGoal)
        btnSaveGoal = findViewById(R.id.btnSaveGoal)
        rvLoggedMeals = findViewById(R.id.rvLoggedMeals)

        val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        dailyGoal = prefs.getInt("daily_goal", 2000)
        etCalorieGoal.setText(dailyGoal.toString())

        loggedMealsAdapter = LoggedMealsAdapter(emptyList()) { meal -> lifecycleScope.launch {
                val dao = AppDatabase.getDatabase(this@CaloriesTrackerFixedActivity).caloriesDoa()
                dao.deleteCalories(meal)
                updateDailySummary()
            }
        }

        rvLoggedMeals.adapter = loggedMealsAdapter
        rvLoggedMeals.layoutManager = LinearLayoutManager(this)

        btnSaveGoal.setOnClickListener {
            val goalInput = etCalorieGoal.text.toString().toIntOrNull()
            if (goalInput != null && goalInput > 0) {
                dailyGoal = goalInput
                prefs.edit().putInt("daily_goal", dailyGoal).apply()
                Toast.makeText(this, "Goal saved!", Toast.LENGTH_SHORT).show()
                progressBarCalories.max = dailyGoal
            } else {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        btnLogMeal.setOnClickListener {
            val intent = Intent(this, RecipeSearchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateDailySummary()
    }

    private fun updateDailySummary() {
        val caloriesDao = AppDatabase.getDatabase(this).caloriesDoa()
        lifecycleScope.launch {
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val currentMonth = today.substring(0, 7)
            val allCalories = caloriesDao.getAllCalories()

            val todayCalories = allCalories.filter { it.date == today }
            val caloriesToday = todayCalories.sumOf { it.total_calories }
            val caloriesMonth = allCalories.filter { it.date.startsWith(currentMonth) }.sumOf { it.total_calories }
            val proteinToday = todayCalories.sumOf { it.protein }
            val carbsToday = todayCalories.sumOf { it.carbs }
            val fatToday = todayCalories.sumOf { it.fat }
            val sodiumToday = todayCalories.sumOf { it.sodium }

            tvCaloriesToday.text = "${caloriesToday.toInt()} kcal"
            tvCaloriesMonth.text = "${caloriesMonth.toInt()} kcal"
            tvProteinToday.text = "Protein: ${proteinToday.toInt()}g"
            tvCarbsToday.text = "Carbs: ${carbsToday.toInt()}g"
            tvFatToday.text = "Fat: ${fatToday.toInt()}g"
            tvSodiumToday.text = "Sodium: ${sodiumToday.toInt()}mg"

            progressBarCalories.max = dailyGoal
            progressBarCalories.progress = caloriesToday.toInt().coerceAtMost(dailyGoal)

            loggedMealsAdapter.updateMeals(todayCalories)
        }
    }
}