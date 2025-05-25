package com.example.proyectofinal


import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.proyectofinal.viewmodel.AdviceViewModel
import com.example.proyectofinal.R
import androidx.compose.runtime.*





@Composable
fun PantallaAvisos(navController: NavController, viewModel: AdviceViewModel = viewModel()) {
    val consejo by viewModel.advice.collectAsState()

    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Efecto para iniciar música al entrar a la pantalla
    LaunchedEffect(Unit) {
        val player = MediaPlayer.create(context, R.raw.musicagato)
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
                .background(color = Color.White)
                .padding(padding),
            contentAlignment = Alignment.Center,

        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.fondoestrellas)
                    .decoderFactory(GifDecoder.Factory())
                    .build(),
                contentDescription = "GIF animado",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .background(color = Color.Green)
                    .fillMaxSize()
            )


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(color = Color.Blue)
                    .fillMaxWidth(0.9f)
                    .border(
                        width = 4.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(0.dp)
                    )

            ) {
                    Text(
                        text = consejo,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth()


                    )
                Image(
                    painter = painterResource(id = R.drawable.gatopro),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .background(color = Color.Green)
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                )
            }
            Button(
                onClick = { viewModel.getAdvice() },
                colors = buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 45.dp)
                    .size(width = 150.dp, height = 50.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(203, 129, 235), Color(124, 20, 250))
                        )
                    )

            ) {
                Text("Otro Consejo",color = Color.White,)
            }
        }
    }
}