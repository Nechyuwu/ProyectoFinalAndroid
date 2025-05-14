package com.example.proyectofinal.ApiAdvice

import retrofit2.Response
import retrofit2.http.GET

interface AdviceApiService {

    @GET("advice")
    suspend fun getRandomAdvice(): Response<Advice>
}