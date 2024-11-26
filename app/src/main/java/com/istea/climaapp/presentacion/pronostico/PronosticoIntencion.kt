package com.istea.climaapp.presentacion.pronostico

sealed class PronosticoIntencion {
    data object Update : PronosticoIntencion()
}