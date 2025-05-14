package com.example.proyectofinal.ApiAdvice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.adviceslip.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val adviceApiService: AdviceApiService = retrofit.create(AdviceApiService::class.java)
}