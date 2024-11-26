package com.istea.climaapp.repositorio.modelo

import kotlinx.serialization.Serializable

@Serializable
data class PronosticoDTO (
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<ListForecast>,
)

@Serializable
data class ListForecast(
    val dt: Long,
    val main: MainForecast,
    val dt_txt: String,
    val weather: List<WeatherForecast>
)

@Serializable
data class WeatherForecast (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class MainForecast(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Long,
    val sea_level: Long,
    val grnd_level: Long,
    val humidity: Long,
    val temp_kf: Double,
)