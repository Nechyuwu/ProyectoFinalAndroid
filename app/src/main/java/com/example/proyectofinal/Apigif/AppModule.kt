package com.example.proyectofinal.Apigif

import com.example.proyectofinal.Constants
import com.example.proyectofinal.ViewModels.GiphyViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = retrofit.create(GiphyApi::class.java)
val repository = GiphyRepository(api)
val viewModel = GiphyViewModel(repository)

