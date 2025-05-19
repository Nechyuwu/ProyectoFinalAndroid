package com.example.proyectofinal


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.proyectofinal.viewmodel.AdviceViewModel
import androidx.compose.runtime.collectAsState




@Composable
fun PantallaAvisos(navController: NavController, viewModel: AdviceViewModel = viewModel()){
    val consejo by viewModel.advice.collectAsState()


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
                    colors = buttonColors(containerColor = Color(255, 255, 255)),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(color = Color.White)

                        .size(150.dp, 100.dp)
                        .border(
                            width = 4.dp,
                            color = Color.Black,
                            shape = RectangleShape,
                        )

                ) {
                    Text("Otro Consejo", color = Color.Black,)
                }
            }
        }
    }

