package com.istea.climaapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.istea.climaapp.presentacion.pages.CiudadPage
import com.istea.climaapp.presentacion.pages.ClimaPage
import com.istea.climaapp.router.NavTarget

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPage() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = NavTarget.Cities.id
    ) {

        composable(
            route = NavTarget.Cities.id
        ) {
            CiudadPage(navHostController)
        }
        composable(
            route = "weather?lat={lat}&lon={lon}&name={name}",
            arguments = listOf(
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType },
                navArgument("name") { type = NavType.StringType }
            )
        ) {
            val lat = it.arguments?.getFloat("lat") ?: 0.0f
            val lon = it.arguments?.getFloat("lon") ?: 0.0f
            val name = it.arguments?.getString("name") ?: ""
            ClimaPage(navHostController, lat, lon, name)
        }
    }
}