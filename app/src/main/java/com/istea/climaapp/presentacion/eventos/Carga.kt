package com.istea.climaapp.presentacion.eventos

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.istea.climaapp.presentacion.iconos.IconManager
import com.istea.climaapp.ui.theme.ClimaAppTheme

@Composable
fun CargaView() {
    EventView {
        EventImage(IconManager.loadingIcon)
        EventText("Cargando...")
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingViewPreview() {
    ClimaAppTheme {
        CargaView()
    }
}