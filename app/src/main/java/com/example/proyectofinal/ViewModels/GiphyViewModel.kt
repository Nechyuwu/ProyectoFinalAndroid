package com.example.proyectofinal.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.Apigif.GiphyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GiphyViewModel(private val repository: GiphyRepository) : ViewModel() {

    private val _gifUrl = MutableStateFlow("https://api.giphy.com/v1/gifs/random?api_key=Nq4OJKe9e1ZMzXpfW4g594Sot1yJO18c&tag=&rating=g")
    val gifUrl: StateFlow<String> = _gifUrl
    init {
        loadRandomGif()
    }




    fun loadRandomGif(tag: String = "") {
        viewModelScope.launch {
            try {
                Log.v("","Tag recibido: $tag")
                val url = repository.getRandomGif(tag)
                _gifUrl.value = url
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
//https://media0.giphy.com/media/v1.Y2lkPWZmOWM4NmYzbmlsdjVmMWs2OXpxcXN2cm5rYng2ODQ4dWthY3k3bGF3Nm11cW14dSZlcD12MV9naWZzX3JhbmRvbSZjdD1n/3oz8xOPiJiuQgOfAw8/giphy.gif