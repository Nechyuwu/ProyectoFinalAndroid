package com.example.proyectofinal.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.Apigif.GiphyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GiphyViewModel(private val repository: GiphyRepository) : ViewModel() {
    private val _gifUrl = MutableStateFlow("Cargando gifs...")
    val gifUrl: StateFlow<String> = _gifUrl
    init {
        loadRandomGif()
    }

    fun loadRandomGif() {
        viewModelScope.launch {
            try {
                val url = repository.getRandomGif()
                _gifUrl.value = url
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}