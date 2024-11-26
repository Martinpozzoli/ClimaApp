package com.istea.climaapp.presentacion.clima

import com.istea.climaapp.repositorio.modelo.Clima

sealed class ClimaEstado {
    data object Empty : ClimaEstado()
    data object Loading : ClimaEstado()
    data class Success(val clima: Clima) : ClimaEstado()
    data class Error(val mensaje: String) : ClimaEstado()
}
