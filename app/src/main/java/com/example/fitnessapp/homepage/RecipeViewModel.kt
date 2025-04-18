package com.example.fitnessapp.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.db.Recipe
import com.example.fitnessapp.db.RecipeResponse
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchRecipes(query: String) {
        viewModelScope.launch {
            try {
                val appId = "9781b81f"
                val appKey = "647a632861fb6525d55a3d2305ea3842"
                val user = "Rubitrejo"

                val response = RecipeRetrofit.api.getRecipes(
                    query = query,
                    appId = appId,
                    appKey = appKey,
                    user = user
                )
                _recipes.value = response.hits.map { it.recipe }
            } catch (e: Exception) {
                _error.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}