package com.istea.climaapp.presentacion

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.istea.climaapp.presentacion.iconos.IconModel
import com.istea.climaapp.ui.theme.ClimaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageView(topBarContent: @Composable (Modifier) -> Unit = {}, content: @Composable (Modifier) -> Unit) {
    val modifier: Modifier = Modifier
    val scrollState = rememberScrollState()

    Scaffold (
        modifier = modifier
            .fillMaxSize(),
        {
            TopAppBar(
                title = {
                    Row(
                        modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        topBarContent(Modifier)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier
                .padding(paddingValues)
                .padding(top = 15.dp)
                .padding(horizontal = 25.dp)
                .verticalScroll(scrollState)
        ) {
            content(modifier)
        }
    }
}

@Composable
fun DisplayIcon(icon: IconModel, size: Dp, color: Color = Color.Unspecified) {
    Icon(
        painter = painterResource(id = icon.imageVector),
        contentDescription = icon.description,
        modifier = Modifier
            .width(size)
            .aspectRatio(1f),
        tint = color
    )
}

@Composable
fun CreateButton(action: () -> Unit, content: @Composable () -> Unit) {
    IconButton (
        modifier = Modifier
            .padding(end = 8.dp),
        onClick = {action()},
        colors = IconButtonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f)
        ),
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PageViewPreview() {
    ClimaAppTheme() {
        PageView{
            Text("Hubo un error")
        }
    }
}