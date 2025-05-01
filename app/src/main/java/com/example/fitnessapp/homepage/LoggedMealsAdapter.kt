package com.example.fitnessapp.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.db.CaloriesConsumed

class LoggedMealsAdapter(
    private var meals: List<CaloriesConsumed>
) : RecyclerView.Adapter<LoggedMealsAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMealInfo: TextView = itemView.findViewById(R.id.tvMealInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_logged_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.tvMealInfo.text = "${meal.total_calories.toInt()} kcal, Protein: ${meal.protein.toInt()}g, Carbs: ${meal.carbs.toInt()}g, Fat: ${meal.fat.toInt()}g, Sodium: ${meal.sodium.toInt()}mg"
    }

    override fun getItemCount(): Int = meals.size

    fun updateMeals(newMeals: List<CaloriesConsumed>) {
        meals = newMeals
        notifyDataSetChanged()
    }
}