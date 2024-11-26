package com.istea.climaapp.presentacion.ciudad

import com.istea.climaapp.repositorio.modelo.Ciudad

sealed class CiudadEstado {
    data object Empty : CiudadEstado()
    data object Loading : CiudadEstado()
    data class Success(val ciudades: List<Ciudad>) : CiudadEstado()
    data class Error(val mensaje: String) : CiudadEstado()
}