package com.example.fitnessapp.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.db.CaloriesConsumed
import com.bumptech.glide.Glide

class LoggedMealsAdapter(
    private var meals: List<CaloriesConsumed>,
    private val onDeleteClick: (CaloriesConsumed) -> Unit) : RecyclerView.Adapter<LoggedMealsAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFoodName: TextView = itemView.findViewById(R.id.tvLoggedFoodName)
        val ivFoodImage: ImageView = itemView.findViewById(R.id.ivLoggedFoodImage)
        val tvCalories: TextView = itemView.findViewById(R.id.tvLoggedCalories)
        val tvProtein: TextView = itemView.findViewById(R.id.tvLoggedProtein)
        val tvCarbs: TextView = itemView.findViewById(R.id.tvLoggedCarbs)
        val tvFat: TextView = itemView.findViewById(R.id.tvLoggedFat)
        val tvSodium: TextView = itemView.findViewById(R.id.tvLoggedSodium)
        //val tvMealInfo: TextView = itemView.findViewById(R.id.tvMealInfo)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_logged_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        holder.tvFoodName.text = meal.foodName
        holder.tvCalories.text = "Calories: ${meal.total_calories.toInt()} kcal"
        holder.tvProtein.text = "Protein: ${meal.protein.toInt()}g"
        holder.tvCarbs.text = "Carbs: ${meal.carbs.toInt()}g"
        holder.tvFat.text = "Fat: ${meal.fat.toInt()}g"
        holder.tvSodium.text = "Sodium: ${meal.sodium.toInt()}mg"
        //holder.tvMealInfo.text = ""

        holder.btnDelete.setOnClickListener {
            onDeleteClick(meal)
        }

        Glide.with(holder.itemView.context)
            .load(meal.imageUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.ivFoodImage)
    }

    override fun getItemCount(): Int = meals.size

    fun updateMeals(newMeals: List<CaloriesConsumed>) {
        meals = newMeals
        notifyDataSetChanged()
    }
}