package com.example.proyectofinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.ApiAdvice.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdviceViewModel : ViewModel() {

    private val _advice = MutableStateFlow("Cargando consejo...")
    val advice: StateFlow<String> = _advice

    init {
        getAdvice()
    }

    fun getAdvice() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRandomAdvice()
                if (response.isSuccessful) {
                    _advice.value = response.body()?.slip?.advice ?: "Consejo no disponible"
                } else {
                    _advice.value = "Error al obtener consejo"
                }
            } catch (e: Exception) {
                _advice.value = "Error: ${e.message}"
            }
        }
    }
}