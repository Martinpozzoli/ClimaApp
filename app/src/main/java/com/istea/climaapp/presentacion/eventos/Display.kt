package com.istea.climaapp.presentacion.eventos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.istea.climaapp.presentacion.iconos.IconModel
import com.istea.climaapp.ui.theme.displayFontFamily

@Composable
fun EventView(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

@Composable
fun EventText(text: String, size: TextUnit = MaterialTheme.typography.headlineSmall.fontSize, colorType: Color? = null) {
    Text(
        text,
        color = colorType ?: MaterialTheme.colorScheme.primary,
        fontFamily = displayFontFamily,
        fontSize = size,
        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
    )
}

@Composable
fun EventImage(icon: IconModel? = null, color: Color? = null) {

    if (icon != null) {
        Icon(
            painter = painterResource(id = icon.imageVector),
            contentDescription = icon.description,
            modifier = Modifier
                .padding(25.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .alpha(0.9f),
            tint = color ?: MaterialTheme.colorScheme.primary
        )
    } else {
        Text("Icono no encontrado")
    }

}
