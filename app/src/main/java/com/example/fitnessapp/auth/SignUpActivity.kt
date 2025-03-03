package com.example.fitnessapp.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fitnessapp.MainActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.db.AppDatabase
import com.example.fitnessapp.db.User
import com.example.fitnessapp.db.UserDao
import kotlinx.coroutines.launch

class SignUpActivity: AppCompatActivity() {
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val usernameTextView= findViewById<EditText>(R.id.etvNewUsername)
        val newPasswordTextView= findViewById<EditText>(R.id.etvNewPassword)
        val signupButton= findViewById<Button>(R.id.btnSignUp)

        val db= AppDatabase.getDatabase(this)
        userDao= db.userDao()

        signupButton.setOnClickListener {
            val username= usernameTextView.text.toString().trim()
            val password= newPasswordTextView.text.toString().trim()

            if(username.isNotEmpty() && password.isNotEmpty())
            {
                lifecycleScope.launch {
                    val existingUser= userDao.getUser(username, password)
                    if(existingUser == null)
                    {
                        userDao.insertUser(User(username= username, password = password))
                        Toast.makeText(applicationContext, "Account Registered Successfully!", Toast.LENGTH_SHORT).show()

                        val intent= Intent(this@SignUpActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else
                    {
                        Toast.makeText(applicationContext, "Username Already Exists!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(applicationContext, "Please enter all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}