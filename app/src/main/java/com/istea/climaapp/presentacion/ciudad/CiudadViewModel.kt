package com.istea.climaapp.presentacion.ciudad

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.istea.climaapp.repositorio.Repositorio
import com.istea.climaapp.repositorio.modelo.Ciudad
import com.istea.climaapp.router.IRouter
import com.istea.climaapp.router.NavTarget
import kotlinx.coroutines.launch

class CiudadViewModel(
    val repository: Repositorio,
    private val navigator: IRouter
) : ViewModel() {
    var estado by mutableStateOf<CiudadEstado>(CiudadEstado.Empty)
    private var ciudades: List<Ciudad> = emptyList()

    fun execute(intention: CiudadIntencion) {
        when(intention) {
            is CiudadIntencion.Search -> searchCity(intention.ciudadNombre)
            is CiudadIntencion.Select -> selectCity(intention.ciudad)
            is CiudadIntencion.SelectUbicacion -> redirigirPorUbicacion(intention.lat, intention.lon)
        }
    }

    private fun searchCity(cityName: String) {
        estado = CiudadEstado.Loading
        viewModelScope.launch {
            try {
                ciudades = repository.getCiudades(cityName) ?: emptyList()
                estado = if(ciudades.isEmpty()) {
                    CiudadEstado.Empty
                } else {
                    CiudadEstado.Success(ciudades)
                }
            } catch (exception: Exception) {
                estado = CiudadEstado.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun selectCity(city: Ciudad) {
        val route = NavTarget.Weather(city.lat, city.lon, city.name)
        navigator.navigate(route)
    }

    private fun redirigirPorUbicacion(lat: Float, long: Float) {
        val route = NavTarget.Weather(lat, long, "Tu ubicación")
        navigator.navigate(route)
    }
}

class CiudadViewModelFactory(
    private val repository: Repositorio,
    private val navigator: IRouter
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadViewModel::class.java)) {
            return CiudadViewModel(repository, navigator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}