package com.example.proyectofinal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.proyectofinal.ViewModels.GiphyViewModel
import com.example.proyectofinal.viewmodel.AdviceViewModel

@Composable
fun PantallaGifs(navController: NavController){
   // val gifs by viewModel.gifUrl.collectAsState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            Button(onClick = { navController.popBackStack("inicio", inclusive = false) },
                modifier = Modifier.padding(top = 20.dp, start = 15.dp)) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Arrow Back ")
                Text("Volver al Inicio")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()

                .padding(padding),
            contentAlignment = Alignment.Center,

            ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("hola")

        /*if (gifs.isNotEmpty()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(gifs)
                    .crossfade(true)
                    .build(),
                contentDescription = "Random GIF",
                modifier = Modifier
                    .size(10.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.loadRandomGif() }) {
            Text(text = "Obtener GIF aleatorio")
        }*/
    }
    }
}
}