package com.istea.climaapp.presentacion.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.istea.climaapp.presentacion.CreateButton
import com.istea.climaapp.presentacion.DisplayIcon
import com.istea.climaapp.presentacion.PageView
import com.istea.climaapp.presentacion.clima.ClimaEstado
import com.istea.climaapp.presentacion.clima.ClimaView
import com.istea.climaapp.presentacion.clima.ClimaViewModel
import com.istea.climaapp.presentacion.clima.ClimaViewModelFactory
import com.istea.climaapp.presentacion.iconos.IconManager
import com.istea.climaapp.presentacion.iconos.IconSize
import com.istea.climaapp.presentacion.pronostico.PronosticoView
import com.istea.climaapp.presentacion.pronostico.PronosticoViewModel
import com.istea.climaapp.presentacion.pronostico.PronosticoViewModelFactory
import com.istea.climaapp.repositorio.NetworkRepositorio
import com.istea.climaapp.repositorio.modelo.Clima
import com.istea.climaapp.router.NavTarget
import com.istea.climaapp.router.Router
import com.istea.climaapp.utils.Compartir

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClimaPage(
    navigator: NavHostController,
    lat: Float,
    lon: Float,
    name: String
) {
    val weatherViewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModelFactory(
            repository = NetworkRepositorio(),
            router = Router(navigator),
            lat = lat,
            lon = lon,
            name = name
        )
    )

    val pronosticoViewModel : PronosticoViewModel = viewModel(
        factory = PronosticoViewModelFactory(
            repository = NetworkRepositorio(),
            router = Router(navigator),
            name = name
        )
    )

    PageView(
        topBarContent = {
            CreateShareButton(weatherViewModel.state)

            var isExpanded by remember { mutableStateOf(false) }
            val dismiss: () -> Unit = { isExpanded = !isExpanded }

            CreateButton(action = {
                dismiss()
                val route = NavTarget.Cities.id
                navigator.navigate(route)
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home button"
                )
            }
        }
    ) {
        ClimaView(
            state = weatherViewModel.state,
            execute = { intention ->
                weatherViewModel.execute(intention)
            }
        )
        PronosticoView(
            state = pronosticoViewModel.state,
            execute = { intention ->
                pronosticoViewModel.execute(intention)
            }
        )
    }
}

@Composable
private fun CreateShareButton(state: ClimaEstado) {
    val weatherDTO = when (state) {
        is ClimaEstado.Success -> state.clima
        else -> Clima()
    }

    val context = LocalContext.current
    val shareManager = Compartir(context)
    CreateButton(action = { shareManager.compartirClima(weatherDTO) }) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "Share button"
        )
    }
}