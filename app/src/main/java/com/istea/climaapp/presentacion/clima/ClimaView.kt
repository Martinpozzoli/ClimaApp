package com.istea.climaapp.presentacion.clima

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.istea.climaapp.presentacion.DisplayIcon
import com.istea.climaapp.presentacion.eventos.CargaView
import com.istea.climaapp.presentacion.eventos.ErrorView
import com.istea.climaapp.presentacion.eventos.NoResultView
import com.istea.climaapp.presentacion.iconos.IconManager
import com.istea.climaapp.presentacion.iconos.IconModel
import com.istea.climaapp.presentacion.iconos.IconSize
import com.istea.climaapp.presentacion.iconos.getWeatherIcon
import com.istea.climaapp.repositorio.modelo.Clima
import com.istea.climaapp.repositorio.modelo.Clouds
import com.istea.climaapp.repositorio.modelo.Coord
import com.istea.climaapp.repositorio.modelo.Main
import com.istea.climaapp.repositorio.modelo.Weather
import com.istea.climaapp.repositorio.modelo.Wind
import com.istea.climaapp.ui.theme.ClimaAppTheme
import com.istea.climaapp.ui.theme.displayFontFamily

@Composable
fun ClimaView(
    state: ClimaEstado,
    execute: (ClimaIntencion) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        execute(ClimaIntencion.Update)
    }
    when(state) {
        is ClimaEstado.Loading -> CargaView()
        is ClimaEstado.Error ->  ErrorView(state.mensaje)
        is ClimaEstado.Success -> SuccessfulView(state.clima)
        is ClimaEstado.Empty -> NoResultView()
    }
}

@Composable
fun SuccessfulView(currentWeather: Clima) {
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreateTempBox(currentWeather.name, currentWeather.main.temp.toInt().toString(), currentWeather.main.feels_like.toInt().toString())
        WeatherInfoRow(currentWeather.weather[0].main, currentWeather.weather[0].icon)
    }
    WeatherStats(currentWeather.wind.speed.toString(), currentWeather.rain.rain.toString(), currentWeather.main.humidity.toString())
    Spacer(Modifier.height(10.dp))
}

@Composable
private fun CreateTempBox(cityName: String, temperature: String, fellLike: String) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        TextDisplay(cityName, MaterialTheme.typography.headlineMedium.fontSize, overflow = true)
        TextDisplay(
            temperature+"º",
            MaterialTheme.typography.displayLarge.fontSize,
        )
        TextDisplay(
            "Sensación térmica: $fellLike º",
            MaterialTheme.typography.bodySmall.fontSize,
            Modifier.offset(y = (-10).dp)
        )
    }
}

@Composable
private fun WeatherInfoRow(weatherDescription: String, icon: String) {
    val modifier: Modifier = Modifier
    Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DisplayIcon(getWeatherIcon(icon), IconSize.extraLarge)
            Spacer(modifier.height(5.dp))
            TextDisplay(weatherDescription, MaterialTheme.typography.headlineMedium.fontSize, overflow = true)
        }
    }

@Composable
fun WeatherStats(wind: String, rain: String, humidity: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(25.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherStatsDisplay("$rain mm", IconManager.thermometerIcon)
        WeatherStatsDisplay("$wind m/s", IconManager.windIcon)
        WeatherStatsDisplay("$humidity %", IconManager.humidityIcon)
    }
}


@Composable
private fun WeatherStatsDisplay(value: String, icon: IconModel) {
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            DisplayIcon(icon, IconSize.small, MaterialTheme.colorScheme.primary)
            Text(
                text = value,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = displayFontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun TextDisplay(text: String, size: TextUnit, modifier: Modifier? = null, overflow: Boolean = false) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = displayFontFamily,
        fontSize = size,
        modifier = modifier ?: Modifier,
        maxLines = 1,
        overflow = if (overflow) { TextOverflow.Ellipsis } else {
            TextOverflow.Visible
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SuccessfulViewPreview() {
    ClimaAppTheme {
        val weather = Clima(
            base = "stations",
            name = "Buenos Aires",
            coord = Coord(lon = -58.4438, lat = -34.6118),
            weather = listOf(
                Weather(id = 800, main = "Clear", description = "Despejado", icon = "01d")
            ),
            main = Main(
                temp = 25.0,
                feels_like = 27.0,
                temp_min = 22.0,
                temp_max = 28.0,
                pressure = 1012,
                humidity = 60
            ),
            wind = Wind(speed = 5.0, deg = 180),
            clouds = Clouds(all = 0)
        )

        SuccessfulView(weather)
    }
}