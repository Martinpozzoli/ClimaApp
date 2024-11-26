package com.istea.climaapp.router

interface IRouter {
    fun navigate(route: NavTarget)
}

sealed class NavTarget(val id: String) {
    data object Cities : NavTarget("cities")
    data class Weather(val lat: Float, val lon: Float, val name: String) : NavTarget("weather")
}