package com.example.fitnessapp.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.db.UserDao

class LoginActivity: AppCompatActivity() {
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}