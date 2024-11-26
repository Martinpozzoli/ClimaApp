package com.istea.climaapp.presentacion.ciudad

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.istea.climaapp.obtenerUbicacion
import com.istea.climaapp.presentacion.eventos.CargaView
import com.istea.climaapp.presentacion.eventos.ErrorView
import com.istea.climaapp.presentacion.eventos.NoResultView
import com.istea.climaapp.repositorio.modelo.Ciudad

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CiudadView(
    state: CiudadEstado,
    execute: (CiudadIntencion) -> Unit
) {
    var input by remember { mutableStateOf("") }
    val context = LocalContext.current

    TextField(
        value = input,
        label = { Text("Busca tu ciudad") },
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {
            input = it
            if(input.isNotEmpty()) {
                execute(CiudadIntencion.Search(it))
            }
        }
    )
    Button(
        onClick = {
            obtenerUbicacion(context) { lat, lon ->
                execute(CiudadIntencion.SelectUbicacion(lat.toFloat(), lon.toFloat()))
            }
        },
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text("Usar mi ubicación actual")
    }

    when(state) {
        is CiudadEstado.Loading -> CargaView()
        is CiudadEstado.Error ->  ErrorView(state.mensaje)
        is CiudadEstado.Success -> LoadCityList(state.ciudades) {
            execute(CiudadIntencion.Select(it))
        }
        is CiudadEstado.Empty -> NoResultView()
    }
}

@Composable
private fun LoadCityList(cities: List<Ciudad>, onSelect: (Ciudad) -> Unit) {
    val modifier = Modifier
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (city in cities) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RectangleShape,
                onClick = { onSelect(city) }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = city.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}
