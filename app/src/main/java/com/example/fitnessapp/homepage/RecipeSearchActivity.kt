package com.example.fitnessapp.homepage

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessapp.R
import kotlinx.coroutines.launch

class RecipeSearchActivity : AppCompatActivity() {

    private lateinit var etSearch: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvRecipes: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_search)

        etSearch = findViewById(R.id.etSearch)
        btnSearch = findViewById(R.id.btnSearch)
        rvRecipes = findViewById(R.id.rvRecipes)

        recipeAdapter = RecipeAdapter(mutableListOf()) { recipeHit ->
            //For now it just toasts it to confirm it's wired
            Toast.makeText(this, "Logged ${recipeHit.recipe.label}", Toast.LENGTH_SHORT).show()
        }
        rvRecipes.adapter = recipeAdapter
        rvRecipes.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                searchRecipes(query)
            } else {
                Toast.makeText(this, "Please enter a food", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun searchRecipes(query: String) {
        lifecycleScope.launch {
            try {
                val response = RecipeRetrofit.api.getRecipes(
                    user = "Rubitrejo",
                    query = query,
                    appId = "9781b81f",
                    appKey = "647a632861fb6525d55a3d2305ea3842"
                )
                val hits = response.hits
                recipeAdapter.updateData(hits)
            } catch (e: Exception) {
                Toast.makeText(this@RecipeSearchActivity, "Error fetching recipes", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
