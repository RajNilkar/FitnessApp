package com.example.fitnessapp.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.db.RecipeHit

class RecipeAdapter(
    private val recipes: MutableList<RecipeHit>,
    private val onLogClick: (RecipeHit) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvRecipeName: TextView = itemView.findViewById(R.id.tvRecipeName)
        val tvCalories: TextView = itemView.findViewById(R.id.tvCalories)
        val btnLog: Button = itemView.findViewById(R.id.btnLogRecipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position].recipe
        holder.tvRecipeName.text = recipe.label
        holder.tvCalories.text = "Calories: ${recipe.calories.toInt()} kcal"

        holder.btnLog.setOnClickListener {
            onLogClick(recipes[position])
        }
    }

    fun updateData(newRecipes: List<RecipeHit>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = recipes.size
}
