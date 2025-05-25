package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.Apigif.repository
import com.example.proyectofinal.ViewModels.GiphyViewModel
import com.example.proyectofinal.ui.theme.ProyectofinalTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest

data class OpcionPantalla(
    val titulo: String,
    val imagenRes: Int,
    val ruta: String
)

class MainActivity : ComponentActivity() {

    lateinit var gifi_model: GiphyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        gifi_model = GiphyViewModel(repository)

        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            ProyectofinalTheme {
                AppNavigation(vm_giphy = gifi_model)
            }
        }
    }


@Composable
fun AppNavigation(vm_giphy: GiphyViewModel,  ) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { PantallaInicio(navController) }
        composable("avisos") { PantallaAvisos(navController) }
        composable("gifs") { PantallaGifs(navController, viewModel = vm_giphy) }
        composable("movimiento") { PantallaMovimiento(navController) }
        composable("fantasmas") { Pantallafantasmas(navController) }
      }
    }
}

@Composable
fun OpcionCard(opcion: OpcionPantalla, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(250.dp)
            .clickable { navController.navigate(opcion.ruta) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = opcion.imagenRes),
                contentDescription = opcion.titulo,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Text(
                text = opcion.titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Composable
fun PantallaInicio(navController: NavController) {

    val opciones = listOf(
        OpcionPantalla("Gato De La Sabiduría", R.drawable.gatopro, "avisos"),
        OpcionPantalla("Televisión de Gifs", R.drawable.television, "gifs"),
        OpcionPantalla("Prueba de Rapidez", R.drawable.puno, "movimiento"),
        OpcionPantalla("Detector de fantasmas", R.drawable.fantasmaboton, "fantasmas")

    )

    Box(modifier = Modifier.fillMaxSize()) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.fondoinicio)
                .decoderFactory(GifDecoder.Factory())
                .build(),
            contentDescription = "GIF animado",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxSize(),

        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = "¡Bienvenido!",
                fontSize = 29.sp,
                color = Color.Black,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(10.dp),
                textAlign = TextAlign.Center

            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                items(opciones) { opcion ->
                    OpcionCard(opcion = opcion, navController = navController)
                }
            }
        }
    }
}

