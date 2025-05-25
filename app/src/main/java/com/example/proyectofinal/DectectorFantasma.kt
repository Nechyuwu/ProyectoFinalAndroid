package com.example.proyectofinal

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import android.Manifest
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import coil.decode.GifDecoder


@Composable
fun Pantallafantasmas(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    //musica
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // Efecto para iniciar música al entrar a la pantalla
    LaunchedEffect(Unit) {
        val player = MediaPlayer.create(context, R.raw.musicafantasma)
        player.start()
        mediaPlayer = player
    }

    // Limpiar recursos cuando se abandona el Composable
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
        }
    }

    // Estado para mostrar resultado
    var resultado by remember { mutableStateOf<Int?>(null) }

    // Permisos
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            Toast.makeText(context, "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
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
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier.clip(RoundedCornerShape(50))
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
                .padding(padding)
        ) {
            // Vista de cámara
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx)
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview
                        )
                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(4f),
                    contentAlignment = Alignment.Center

                ){
                    when (resultado) {
                        1 -> {
                            Text(
                                text = "No hay fantasma",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier
                                    .background(
                                        Color.Black.copy(alpha = 0.5f),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .padding(8.dp)
                            )
                        }
                        2 -> {
                            var fantasmaelegir by remember { mutableStateOf<Int?>(null) }
                            fantasmaelegir = (1..2).random()
                            when(fantasmaelegir){
                                1 ->{AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(R.drawable.fantasma)
                                        .decoderFactory(GifDecoder.Factory())
                                        .build(),
                                    contentDescription = "GIF animado",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize(0.8f) // 50% del ancho disponible


                                )}
                                2 ->{AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(R.drawable.fantasma2)
                                        .decoderFactory(GifDecoder.Factory())
                                        .build(),
                                    contentDescription = "GIF animado",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize(0.8f) // 50% del ancho disponible


                                )}

                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ){
                    Button(
                        onClick = {
                            resultado = (1..2).random()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Detectar fantasma")
                    }
                }
            }
        }
    }
}
