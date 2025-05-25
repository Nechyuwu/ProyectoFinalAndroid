package com.example.proyectofinal.ApiAdvice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: AdviceApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.adviceslip.com/") // Asegúrate que termine con "/"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AdviceApiService::class.java)
    }
}