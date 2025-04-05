package com.example.fitnessapp.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import com.example.fitnessapp.db.Workout

class ExercisesAdapter(private val exercisesList: List<Exercises>, private val onAddClick: (Workout) -> Unit): RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder>() {

    class ExercisesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val exercisesListTextView= itemView.findViewById<TextView>(R.id.tvExerciseItems)
        val addButton = itemView.findViewById<View>(R.id.fabAddWorkout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_exercises, parent, false)
        return ExercisesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        val exercise= exercisesList[position]
        holder.exercisesListTextView.text= exercise.name

        holder.addButton.setOnClickListener {
            val workout = Workout(name = exercise.name)
            onAddClick(workout)
        }
    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }
}