package com.example.fitnessapp.homepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R

class WorkoutAdapter(val workouts: MutableList<Workout>, val onDeleteClick: (Workout) -> Unit) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val workoutName = itemView.findViewById<TextView>(R.id.tvWorkoutName)
        val setsDone = itemView.findViewById<EditText>(R.id.etWorkoutSets)
        val repsDone = itemView.findViewById<EditText>(R.id.etWorkoutReps)
        val deleteWorkout = itemView.findViewById<Button>(R.id.btnDeleteWorkout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workouts[position]
        holder.workoutName.text = workout.name
        holder.setsDone.setText(workout.sets.toString())
        holder.repsDone.setText(workout.reps.toString())

        holder.setsDone.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val setsText = holder.setsDone.text.toString().trim()
                workout.sets = setsText.toIntOrNull() ?: workout.sets
            }
        }
        holder.repsDone.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val repsText = holder.repsDone.text.toString().trim()
                workout.reps = repsText.toIntOrNull() ?: workout.reps
            }
        }

        holder.deleteWorkout.setOnClickListener {
            onDeleteClick(workout)
        }
    }

    override fun getItemCount(): Int = workouts.size
}