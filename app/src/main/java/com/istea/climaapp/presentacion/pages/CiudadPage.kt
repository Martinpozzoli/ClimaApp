package com.istea.climaapp.presentacion.pages

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.istea.climaapp.presentacion.PageView
import com.istea.climaapp.presentacion.ciudad.CiudadView
import com.istea.climaapp.presentacion.ciudad.CiudadViewModel
import com.istea.climaapp.presentacion.ciudad.CiudadViewModelFactory
import com.istea.climaapp.repositorio.NetworkRepositorio
import com.istea.climaapp.router.Router

@Composable
fun CiudadPage(
    navigator: NavHostController
) {
    val viewModel : CiudadViewModel = viewModel(
        factory = CiudadViewModelFactory(
            repository = NetworkRepositorio(),
            navigator = Router(navigator)
        )
    )

    PageView{
        CiudadView(
            state = viewModel.estado,
            execute = { intention ->
                viewModel.execute(intention)
            }
        )
    }
}