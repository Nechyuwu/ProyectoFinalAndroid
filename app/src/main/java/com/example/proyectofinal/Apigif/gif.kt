package com.example.proyectofinal.Apigif

data class GiphyResponse(
    val data: GiphyData
)

data class GiphyData(
    val images: GiphyImages
)

data class GiphyImages(
    val original: GiphyOriginal
)

data class GiphyOriginal(
    val url: String
)