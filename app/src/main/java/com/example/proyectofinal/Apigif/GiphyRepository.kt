package com.example.proyectofinal.Apigif

import com.example.proyectofinal.Constants

class GiphyRepository(private val api: GiphyApi) {

    suspend fun getRandomGif(tag: String = ""): String {
        val response = api.getRandomGif(Constants.API_KEY,  tag = tag)
    return response.data.images.original.url
    }
}