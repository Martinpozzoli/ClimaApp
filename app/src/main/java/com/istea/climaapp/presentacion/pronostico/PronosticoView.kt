package com.istea.climaapp.presentacion.pronostico

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.istea.climaapp.presentacion.DisplayIcon
import com.istea.climaapp.presentacion.eventos.CargaView
import com.istea.climaapp.presentacion.eventos.ErrorView
import com.istea.climaapp.presentacion.eventos.NoResultView
import com.istea.climaapp.presentacion.iconos.IconSize
import com.istea.climaapp.presentacion.iconos.getWeatherIcon
import com.istea.climaapp.repositorio.modelo.ListForecast
import com.istea.climaapp.ui.theme.displayFontFamily
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PronosticoView(
    state: PronosticoEstado,
    execute: (PronosticoIntencion) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        execute(PronosticoIntencion.Update)
    }
    when(state) {
        is PronosticoEstado.Loading -> CargaView()
        is PronosticoEstado.Error ->  ErrorView(state.mensaje)
        is PronosticoEstado.Success -> SuccessfulView(state.pronostico)
        is PronosticoEstado.Empty -> NoResultView()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SuccessfulView(forecast: List<ListForecast>) {
    Column (
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(25.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            forecast.forEach { item ->
                DayWeatherInfo(getDayOfWeek(item.dt_txt), item.main.temp_max.toInt().toString(),
                    item.main.temp_min.toInt().toString(), item.weather[0].icon)
            }
        }
    }
}

@Composable
private fun DayWeatherInfo(day: String, max: String, min: String, icon: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextDislay(day, MaterialTheme.typography.titleMedium.fontSize)
        Spacer(Modifier.height(4.dp))
        DisplayIcon(getWeatherIcon(icon), IconSize.small)
        Spacer(Modifier.height(4.dp))
        TextDislay(max+"º", MaterialTheme.typography.bodyMedium.fontSize, Modifier.alpha(0.9f))
        TextDislay(min+"º", MaterialTheme.typography.bodyMedium.fontSize, Modifier.alpha(0.8f))
    }
}

@Composable
private fun TextDislay(text: String, size: TextUnit, modifier: Modifier? = null) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = displayFontFamily,
        fontSize = size,
        modifier = modifier ?: Modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun getDayOfWeek(dateTimeString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)
    val dayOfWeek: DayOfWeek = dateTime.dayOfWeek

    return dayOfWeek.getDisplayName(java.time.format.TextStyle.SHORT, Locale("es", "ES"))
        .take(2)
}