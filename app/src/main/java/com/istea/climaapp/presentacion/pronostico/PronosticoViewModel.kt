package com.istea.climaapp.presentacion.pronostico

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.istea.climaapp.repositorio.Repositorio
import com.istea.climaapp.router.Router
import kotlinx.coroutines.launch

class PronosticoViewModel(
    val repository: Repositorio,
    val router: Router,
    val name: String
) : ViewModel() {
    var state by mutableStateOf<PronosticoEstado>(PronosticoEstado.Empty)

    fun execute(intention: PronosticoIntencion) {
        when(intention) {
            is PronosticoIntencion.Update -> loadForecast()
        }
    }

    private fun loadForecast() {
        state = PronosticoEstado.Loading

        viewModelScope.launch {
            try {
                val forecast = repository.getPronostico(name).filter { true }
                state = PronosticoEstado.Success(forecast)
            } catch (exception: Exception) {
                state = PronosticoEstado.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }
}

class PronosticoViewModelFactory(
    private val repository: Repositorio,
    private val router: Router,
    private val name: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PronosticoViewModel::class.java)) {
            return PronosticoViewModel(repository, router, name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}