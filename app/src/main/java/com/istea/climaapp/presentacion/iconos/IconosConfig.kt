package com.istea.climaapp.presentacion.iconos

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.istea.climaapp.R

data class IconModel(
    val name: String,
    val imageVector: Int,
    val description: String,
    val size: Dp = IconSize.small
)

object IconSize {
    val extraSmall = 26.dp
    val small = 38.dp
    val medium = 72.dp
    val large = 96.dp
    val extraLarge = 150.dp
}

object IconManager {
    val errorIcon = IconModel(
        name = "error",
        imageVector = R.drawable.baseline_error_outline_24,
        description = "Icono de error"
    )

    val loadingIcon = IconModel(
        name = "loading",
        imageVector = R.drawable.baseline_share_location_24,
        description = "Icono de carga"
    )

    val emptyIcon = IconModel(
        name = "empty",
        imageVector = R.drawable.baseline_self_improvement_24,
        description = "Icono de vacio"
    )

    val windIcon = IconModel(
        name = "Wind",
        imageVector = R.drawable.baseline_air_24,
        description = "Icono de vieto"
    )

    val thermometerIcon = IconModel(
        name = "Wind",
        imageVector = R.drawable.baseline_thermostat_24,
        description = "Icono de termometro"
    )

    val humidityIcon = IconModel(
        name = "Wind",
        imageVector = R.drawable.baseline_water_drop_24,
        description = "Icono de humedad"
    )

    val cityIcon = IconModel(
        name = "City",
        imageVector = R.drawable.baseline_location_city_24,
        description = "Icono de ciudad"
    )

    val themeIcon = IconModel(
        name = "Theme",
        imageVector = R.drawable.baseline_sunny_24,
        description = "Icono de tema"
    )
}

fun getWeatherIcon(iconCode: String): IconModel {
    val iconName = when (iconCode) {
        "01d", "01n" -> "Clear"
        "02d", "02n", "03d", "03n", "04d", "04n" -> "Clouds"
        "09d", "09n", "10d", "10n" -> "Rain"
        "11d", "11n" -> "Thunderstorm"
        "13d", "13n" -> "Snow"
        "50d", "50n" -> "Atmosphere"
        else -> iconCode
    }

    val iconResource = weatherIconMap[iconName] ?: R.drawable.baseline_error_outline_24

    return IconModel(
        name = iconName,
        imageVector = iconResource,
        description = "Icono de $iconName"
    )
}

val weatherIconMap = mapOf(
    "Thunderstorm" to R.drawable.baseline_thunderstorm_24,
    "Drizzle" to R.drawable.baseline_cloud_24,
    "Rain" to R.drawable.baseline_water_drop_24,
    "Snow" to R.drawable.baseline_cloudy_snowing_24,
    "Atmosphere" to R.drawable.baseline_waves_24,
    "Clear" to R.drawable.baseline_sunny_24,
    "Clouds" to R.drawable.baseline_cloud_24
)