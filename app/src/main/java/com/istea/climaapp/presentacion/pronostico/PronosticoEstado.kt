package com.istea.climaapp.presentacion.pronostico

import com.istea.climaapp.repositorio.modelo.ListForecast

sealed class PronosticoEstado {
    data object Empty : PronosticoEstado()
    data object Loading : PronosticoEstado()
    data class Success(val pronostico: List<ListForecast>) : PronosticoEstado()
    data class Error(val mensaje: String) : PronosticoEstado()
}