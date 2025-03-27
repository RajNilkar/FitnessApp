package com.example.fitnessapp.homepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ExercisesViewModel: ViewModel() {
    private val exercisesList= MutableLiveData<List<Exercises>>()
    val listOfExercises: LiveData<List<Exercises>> = exercisesList

    init {
        fetchExercises(muscle = "chest")
    }

    public fun fetchExercises(muscle: String) {
        viewModelScope.launch {
            try {
                val response= RetrofitInstance.api.getExercises(muscle)
                exercisesList.postValue(response)
            }
            catch (e:Exception)
            {
                Log.e("ExercisesViewModel", "Failed to fetch exercises: ${e.message}")
            }
        }
    }
}