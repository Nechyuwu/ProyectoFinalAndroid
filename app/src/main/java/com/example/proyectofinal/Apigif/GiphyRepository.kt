package com.example.proyectofinal.Apigif

class GiphyRepository(private val api: GiphyApi) {
    suspend fun getRandomGif(): String {
        val response = api.getRandomGif()
        return response.data.images.original.url
    }
}