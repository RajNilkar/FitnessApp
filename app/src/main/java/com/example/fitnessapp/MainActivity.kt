package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.fitnessapp.auth.SignUpActivity
import com.example.fitnessapp.db.AppDatabase
import com.example.fitnessapp.db.User
import com.example.fitnessapp.db.UserDao
import com.example.fitnessapp.homepage.HomeActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var userDao: UserDao

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
        val registerButton= findViewById<Button>(R.id.btnRegister)

        val db= AppDatabase.getDatabase(this)
        userDao= db.userDao()

        lifecycleScope.launch {
            // Insert test user (if not already present)
            val existingUsers = userDao.getAllUsers()
            if (existingUsers.isEmpty()) {
                val testUser = User(username = "testuser", password = "1234")
                userDao.insertUser(testUser)
                Log.d("Database", "Test user inserted: ${testUser.username}")
            }

            // Log all users to verify insertion
            val allUsers = userDao.getAllUsers()
            Log.d("Database", "Users in DB: $allUsers")
        }


        loginButton.setOnClickListener {
            val username= usernameTextView.text.toString().trim()
            val password= passwordTextView.text.toString().trim()

            lifecycleScope.launch {
                val user= userDao.getUser(username, password)
                val allUsers = userDao.getAllUsers()
                Log.d("Database", "Users in DB: $allUsers")
                if(user != null)
                {
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                    //Need to implement the logic this goes to a new activity page
                    val intent= Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(applicationContext, "Invalid username or password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerButton.setOnClickListener {
            val intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}