package com.istea.climaapp.repositorio

import com.istea.climaapp.presentacion.ClimaEstado
import com.istea.climaapp.repositorio.modelo.Ciudad
import com.istea.climaapp.repositorio.modelo.Clima
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkRepositorio: Repositorio {

    private val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getClima(): ClimaEstado {
        TODO("Not yet implemented")
    }

    override suspend fun getCiudades(ciudad: String): List<Ciudad> {
        TODO("Not yet implemented")
    }

    override suspend fun getPronostico(lat: Float, long: Float): List<Clima> {
        TODO("Not yet implemented")
    }
}