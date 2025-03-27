package com.example.fitnessapp.homepage

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private const val BASE_URL= "https://api.api-ninjas.com/v1/"
        private const val API_KEY= "Tte7Hu0ulvG8UJ5CglfW9A==mXljHZlxuMMOe3Tp"

        val intercept= HttpLoggingInterceptor().apply {
            level= HttpLoggingInterceptor.Level.BODY
        }

        val client= OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request= chain.request().newBuilder()
                    .addHeader("X-Api-Key", API_KEY)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(intercept)
            .build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        val api: ExercisesApi by lazy{
            getRetrofitInstance().create(ExercisesApi::class.java)
        }
    }
}