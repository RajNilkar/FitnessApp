package com.example.fitnessapp.homepage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.fitnessapp.db.AppDatabase

class WeightLogViewModel(application: Application): AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).weightLogDao()

    val weightLogs = dao.getAllWeightLogs().asLiveData()
}