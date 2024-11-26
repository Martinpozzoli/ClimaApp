package com.istea.climaapp.repositorio.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Clima(
    val base: String = "",
    val name: String = "",
    val coord: Coord = Coord(0.0, 0.0),
    val weather: List<Weather> = emptyList(),
    val main: Main = Main(0.0, 0.0, 0.0, 0.0, 0, 0),
    val wind: Wind = Wind(0.0, 0),
    val clouds: Clouds = Clouds(0),
    val rain: Rain = Rain(0.0),
)
@Serializable
data class Coord(
    val lon: Double,
    val lat: Double,
)
@Serializable
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)
@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Long,
    val humidity: Long,
)
@Serializable
data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double? = null,
)

@Serializable
data class Clouds(
    val all: Long,
)

@Serializable
data class Rain(
    val rain: Double = 0.0
)