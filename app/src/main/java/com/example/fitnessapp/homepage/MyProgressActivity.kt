package com.example.fitnessapp.homepage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fitnessapp.R
import com.example.fitnessapp.db.AppDatabase
import com.example.fitnessapp.db.WeightLog
import kotlinx.coroutines.launch

class MyProgressActivity: AppCompatActivity() {
    private lateinit var weightInput: EditText
    private lateinit var weightButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myprogress)

        weightInput = findViewById(R.id.etvWeightInput)
        weightButton = findViewById(R.id.btnAddWeight)

        val db = AppDatabase.getDatabase(this)
        val weightDao = db.weightLogDao()

        //save/ update the user's weight
        weightButton.setOnClickListener {
            val weight = weightInput.text.toString().toFloatOrNull()

            if(weight != null)
            {
                lifecycleScope.launch {
                    val entry = WeightLog(weight = weight)
                    weightDao.insertWeightLog(entry)
                    runOnUiThread {
                        Toast.makeText(this@MyProgressActivity, "Weight logged!", Toast.LENGTH_SHORT).show()
                        weightInput.text.clear()
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Please enter a valid weight.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}