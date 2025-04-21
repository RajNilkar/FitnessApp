package com.example.fitnessapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightLogDao {
    @Insert
    suspend fun insertWeightLog(log: WeightLog)

    @Query("SELECT * FROM weight_log ORDER BY timestamp ASC") //Display in Ascending order
    fun getAllWeightLogs(): Flow<List<WeightLog>>
}