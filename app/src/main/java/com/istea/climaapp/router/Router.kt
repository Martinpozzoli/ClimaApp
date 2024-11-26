package com.istea.climaapp.router

import android.annotation.SuppressLint
import androidx.navigation.NavHostController

class Router (
    val navHostController: NavHostController
): IRouter {
    @SuppressLint("DefaultLocale")
    override fun navigate(route: NavTarget) {
        when(route) {
            is NavTarget.Cities -> navHostController.navigate(route.id)
            is NavTarget.Weather -> {
                val route = String.format(format = "%s?lat=%f&lon=%f&name=%s",route.id, route.lat, route.lon, route.name)
                navHostController.navigate(route)
            }
        }
    }
}