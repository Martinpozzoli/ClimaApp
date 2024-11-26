package com.istea.climaapp.presentacion.eventos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.istea.climaapp.presentacion.iconos.IconManager
import com.istea.climaapp.ui.theme.ClimaAppTheme

@Composable
fun ErrorView(message: String) {
    EventView {
        EventImage(IconManager.errorIcon, MaterialTheme.colorScheme.error)
        EventText("Ocurrió un error", colorType = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height(1.dp))
        Box(
            Modifier
                .padding(horizontal = 2.dp)
                .fillMaxWidth()
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.3f)),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(MaterialTheme.colorScheme.error.copy(alpha = 0.2f))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            EventText(message, MaterialTheme.typography.bodyLarge.fontSize, MaterialTheme.colorScheme.error)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    ClimaAppTheme {
        ErrorView("Error")
    }
}