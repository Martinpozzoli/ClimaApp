package com.istea.climaapp.presentacion.clima

sealed class ClimaIntencion {
    data object Update: ClimaIntencion()
}