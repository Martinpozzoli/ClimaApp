package com.istea.climaapp.repositorio

import com.istea.climaapp.presentacion.ClimaEstado
import com.istea.climaapp.repositorio.modelo.Ciudad
import com.istea.climaapp.repositorio.modelo.Clima

interface Repositorio {
    suspend fun getClima() : ClimaEstado
    suspend fun getCiudades(ciudad: String) : List<Ciudad>
    suspend fun getPronostico(lat: Float, long: Float): List<Clima>
}