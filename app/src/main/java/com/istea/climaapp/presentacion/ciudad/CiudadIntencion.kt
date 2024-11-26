package com.istea.climaapp.presentacion.ciudad

import com.istea.climaapp.repositorio.modelo.Ciudad

sealed class CiudadIntencion {
    data class Search(val ciudadNombre: String) : CiudadIntencion()
    data class Select(val ciudad: Ciudad) : CiudadIntencion()
    data class SelectUbicacion(val lat: Float, val lon: Float) : CiudadIntencion()
}