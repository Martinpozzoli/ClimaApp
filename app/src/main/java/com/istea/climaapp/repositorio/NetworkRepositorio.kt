package com.istea.climaapp.repositorio

import com.istea.climaapp.repositorio.modelo.Ciudad
import com.istea.climaapp.repositorio.modelo.Clima
import com.istea.climaapp.repositorio.modelo.ListForecast
import com.istea.climaapp.repositorio.modelo.PronosticoDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkRepositorio: Repositorio {

    private object Api {
        const val APIKEY = "a7dbe061e846758f3db329a9bf60eeb7"
        const val CIUDAD_ENDPOINT = "https://api.openweathermap.org/geo/1.0/direct"
        const val CLIMA_ENDPOINT = "https://api.openweathermap.org/data/2.5/weather"
        const val PRONOSTICO_ENDPOINT = "https://api.openweathermap.org/data/2.5/forecast"
        const val PRONOSTICO_ENDPOINT2 = "https://api.openweathermap.org/data/2.5/onecall"
    }

    private val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getClima(lat: Float, lon: Float): Clima {
        val response = client.get(Api.CLIMA_ENDPOINT){
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("units","metric")
            parameter("appid", Api.APIKEY)
        }
        validateHttpStatus(response.status)
        return response.body<Clima>()
    }

    override suspend fun getCiudades(ciudad: String): List<Ciudad> {
        val response = client.get(Api.CIUDAD_ENDPOINT){
            parameter("q", ciudad)
            parameter("limit", 10)
            parameter("appid", Api.APIKEY)
        }
        validateHttpStatus(response.status)
        return response.body<List<Ciudad>>()
    }

    override suspend fun getPronostico(name: String): List<ListForecast> {
        val response = client.get(Api.PRONOSTICO_ENDPOINT) {
            parameter("q", name)
            parameter("units", "metric")
            parameter("appid", Api.APIKEY)
        }
        validateHttpStatus(response.status)

        val pronosticos = response.body<PronosticoDTO>().list

        // Tomar el primer registro de cada día
        val registrosUnicosPorDia = pronosticos.groupBy {
            it.dt_txt.substring(0, 10) // Extraer solo la fecha (YYYY-MM-DD)
        }.map { (_, registrosPorDia) ->
            registrosPorDia.first()
        }

        return registrosUnicosPorDia.take(7)
    }

    //Sin uso de momento, en esta consulta seria necesario cambiar el dto para traer el pronostico diario
    override suspend fun getPronostico2(lat: Float, long: Float): List<ListForecast> {
        val response = client.get(Api.PRONOSTICO_ENDPOINT2){
            parameter("lat", lat)
            parameter("lon", long)
            parameter("units","metric")
            parameter("exclude","current,minutely,hourly,alerts")
            parameter("appid",Api.APIKEY)
        }
        validateHttpStatus(response.status)
        return response.body<PronosticoDTO>().list.take(7)
    }

    private fun validateHttpStatus(status: HttpStatusCode): Unit {
        if(status != HttpStatusCode.OK) {
            throw Exception()
        }
    }
}