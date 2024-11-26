package com.istea.climaapp.utils

import android.content.Context
import android.content.Intent
import com.istea.climaapp.repositorio.modelo.Clima

class Compartir(private val context: Context) {
    fun compartirClima(clima: Clima) {
        val mensaje = """
            🌤️ Weather Update 🌤️
            Location: ${clima.name}
            Temperature: ${clima.main.temp}
            Description: ${clima.weather[0].description}
        """.trimIndent()

        mensajeCompartir(mensaje)
    }

    private fun mensajeCompartir(mensaje: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mensaje)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "Estado del clima")
        }
        val shareIntent = Intent.createChooser(intent, null)
        context.startActivity(shareIntent)
    }
}