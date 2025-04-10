package com.example.fitnessapp.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.db.CaloriesBurned
import kotlinx.coroutines.launch

class CaloriesViewModel : ViewModel() {
    private val _caloriesData = MutableLiveData<List<CaloriesBurned>>()
    val caloriesData: LiveData<List<CaloriesBurned>> = _caloriesData

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchCalories(activity: String, duration: Int) {
        viewModelScope.launch {
            try {
                val response = CaloriesRetrofit.api.getCalories(
                    apiKey = "ZVf6D7PQwa6bdMPliVF/fQ==V6IRlIIOgiYtrK8s",
                    activity = activity,
                    duration = duration
                )
                _caloriesData.value = response
            } catch (e: Exception){
                _error.value = "error: ${e.message}"
            }
        }
    }
}