package com.example.fitnessapp.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

class ExercisesAdapter(private val exercisesList: List<Exercises>): RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder>() {

    class ExercisesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val exercisesListTextView= itemView.findViewById<TextView>(R.id.tvExerciseItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_exercises, parent, false)
        return ExercisesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        val exercise= exercisesList[position]
        holder.exercisesListTextView.text= exercise.name
    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }
}