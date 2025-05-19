package com.example.proyectofinal.Apigif

import com.example.proyectofinal.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("random")
    suspend fun getRandomGif(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("tag") tag: String = "",
        @Query("rating") rating: String = "g"
    ): GiphyResponse
}