package com.example.fitnessapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usernameTextView= findViewById<EditText>(R.id.etvUsername)
        val passwordTextView= findViewById<EditText>(R.id.etvPassword)
        val loginButton= findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val username= usernameTextView.text.toString().trim()
            val password= passwordTextView.text.toString().trim()
            loginCredentials(username, password)
        }
    }

    private fun loginCredentials(username: String, password: String)
    {
        if(username == "user" && password == "1234")
        {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }
}