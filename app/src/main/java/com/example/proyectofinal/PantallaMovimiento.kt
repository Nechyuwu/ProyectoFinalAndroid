package com.example.proyectofinal

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay



@Composable
fun PantallaMovimiento(navController: NavController) {
    val context = LocalContext.current
    var count by remember { mutableStateOf(0) }
    var isCounting by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(10) }
    val lifecycleOwner = LocalLifecycleOwner.current
    var mensaje by remember { mutableStateOf("") }



    LaunchedEffect(isCounting) {
        if (isCounting) {
            timeLeft = 10
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            isCounting = false
            if(count <= 5){
                mensaje = "noob"
            };
            if(count >=6){
                mensaje = "proo"
            };
            if (count>=10){
                mensaje = "legend"
            }

        }
    }

    val shakeDetector = remember {
        ShakeDetector(context) {
            if (isCounting) {
                count++
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> shakeDetector.start()
                Lifecycle.Event.ON_PAUSE -> shakeDetector.stop()
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            shakeDetector.stop()
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
    ){padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(padding),
            contentAlignment = Alignment.Center,

            ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Text("( ͡° ͜ʖ ͡°)",
                        fontSize = (count*10).sp,
                        color = Color.Black,)


                        Spacer(modifier = Modifier
                            .height(8.dp))

                        Text("${mensaje}"
                            ,fontSize = 30.sp,
                            color = Color.Black,)

                    }
                }


                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                    contentAlignment = Alignment.CenterStart,
                ){ Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier
                        .height(16.dp))
                    Text("Tiempo restante: $timeLeft s"
                        ,style = MaterialTheme.typography.titleLarge,
                        color = Color.Black)
                    Spacer(modifier = Modifier
                        .height(8.dp))
                    Text("Sacudidas: $count"
                        ,fontSize = 40.sp,
                        color = Color.Black,)
                    Spacer(modifier = Modifier
                        .height(24.dp))
                    Button(
                        onClick = {
                            if (!isCounting) {
                                mensaje = ""
                                count = 0
                                isCounting = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text("Iniciar desafío")
                    }
                  }
                }
            }
        }
    }
}

/**/