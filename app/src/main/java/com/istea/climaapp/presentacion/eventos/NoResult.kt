package com.istea.climaapp.presentacion.eventos

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istea.climaapp.presentacion.iconos.IconManager
import com.istea.climaapp.ui.theme.ClimaAppTheme

@Composable
fun NoResultView() {
    EventView {
        EventImage(IconManager.emptyIcon)
        EventText("Bienvenido")
        Spacer(modifier = Modifier.height(1.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    ClimaAppTheme {
        NoResultView()
    }
}