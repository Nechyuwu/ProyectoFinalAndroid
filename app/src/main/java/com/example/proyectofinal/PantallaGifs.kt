package com.example.proyectofinal

import android.media.MediaPlayer
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.Coil.imageLoader
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.util.DebugLogger

import com.example.proyectofinal.ViewModels.GiphyViewModel
import com.example.proyectofinal.viewmodel.AdviceViewModel

@Composable

fun PantallaGifs(navController: NavController, viewModel: GiphyViewModel) {

    var tag by remember { mutableStateOf("") }
    val gifUrl by viewModel.gifUrl.collectAsState()

    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .logger(DebugLogger())
            .build()
    }

    //musica
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Efecto para iniciar música al entrar a la pantalla
    LaunchedEffect(Unit) {
        val player = MediaPlayer.create(context, R.raw.musicagifs)
        player.start()
        mediaPlayer = player
    }

    // Limpiar recursos cuando se abandona el Composable
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.White),
                contentAlignment = Alignment.CenterStart
            ) {
                Button(
                    onClick = { navController.popBackStack("inicio", inclusive = false) },
                    colors = buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Flecha"
                    )
                    Text("Volver al Inicio", color = Color.Black)
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fondogifs),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .background(color = Color.Green)
                    .fillMaxSize()
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = Color.Gray)
                    .fillMaxWidth(0.9f)
                    .border(4.dp, Color.Black, RoundedCornerShape(0.dp))
                    .padding(16.dp)
            ) {
                TextField(
                    value = tag,
                    onValueChange = { tag = it },
                    label = { Text("¿Que quieres ver en la tele?") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true
                )
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(gifUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "GIF Aleatorio",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .background(color = Color.Black)
                )

                Spacer(modifier = Modifier.height(60.dp))

                Button(onClick = { viewModel.loadRandomGif(tag) },
                    colors = buttonColors(containerColor = Color.Black),
                    modifier = Modifier
                        .size(width = 150.dp, height = 50.dp),
                    shape = RectangleShape


                ) {
                    Text("")
                }
            }
        }
    }
}

/*AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(gifUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "GIF Aleatorio",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                )*/