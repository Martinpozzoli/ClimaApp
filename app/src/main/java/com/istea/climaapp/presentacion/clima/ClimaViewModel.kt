package com.istea.climaapp.presentacion.clima

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.istea.climaapp.repositorio.Repositorio
import com.istea.climaapp.repositorio.modelo.Clima
import com.istea.climaapp.router.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClimaViewModel(
    val repository: Repositorio,
    val router: Router,
    val lat: Float,
    val lon : Float,
    val name: String
) : ViewModel() {
    var state by mutableStateOf<ClimaEstado>(ClimaEstado.Empty)
    private val _weather = MutableStateFlow<Clima>(Clima())
    val weather: StateFlow<Clima> get() = _weather

    fun execute(intention: ClimaIntencion) {
        when(intention) {
            is ClimaIntencion.Update -> loadWeather()
        }
    }

    private fun loadWeather() {
        state = ClimaEstado.Loading

        viewModelScope.launch {
            try {
                val weather = repository.getClima(lat = lat, lon = lon)
                _weather.value = weather
                state = ClimaEstado.Success(weather)
            } catch (exception: Exception) {
                state = ClimaEstado.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }
}

class ClimaViewModelFactory(
    private val repository: Repositorio,
    private val router: Router,
    private val lat: Float,
    private val lon: Float,
    private val name: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
            return ClimaViewModel(repository, router, lat, lon, name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}