package com.istea.climaapp.repositorio

import com.istea.climaapp.repositorio.modelo.Ciudad
import com.istea.climaapp.repositorio.modelo.Clima
import com.istea.climaapp.repositorio.modelo.ListForecast

interface Repositorio {
    suspend fun getClima(lat: Float, lon: Float) : Clima
    suspend fun getCiudades(ciudad: String) : List<Ciudad>
    suspend fun getPronostico(name: String): List<ListForecast>
    suspend fun getPronostico2(lat: Float, long: Float): List<ListForecast>
}