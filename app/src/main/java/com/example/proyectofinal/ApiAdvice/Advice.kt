package com.example.proyectofinal.ApiAdvice

data class Advice(
    val slip: Slip
)

data class Slip(
    val id: Int,
    val advice: String
)