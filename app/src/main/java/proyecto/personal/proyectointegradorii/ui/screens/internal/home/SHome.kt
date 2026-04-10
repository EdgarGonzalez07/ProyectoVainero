package proyecto.personal.proyectointegradorii.ui.screens.internal.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import proyecto.personal.proyectointegradorii.data.remote.dto.platillo.PlatilloDto
import proyecto.personal.proyectointegradorii.ui.components.cards.PlatilloCard
import proyecto.personal.proyectointegradorii.ui.components.headers.HeaderHome
import proyecto.personal.proyectointegradorii.ui.components.modals.PlatilloModal
import proyecto.personal.proyectointegradorii.ui.theme.BackgroundColor
import proyecto.personal.proyectointegradorii.viewmodels.cart.CartViewModel
import proyecto.personal.proyectointegradorii.viewmodels.home.HomeViewModel

@Composable
fun SHome(
    navController: NavController,
    cartViewModel: CartViewModel,
    viewModel: HomeViewModel = viewModel()
) {

    val platillos by viewModel.platillos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var selectedPlatillo by remember { mutableStateOf<PlatilloDto?>(null) }
    var showModal by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        HeaderHome(
            query,
            { query = it },
            onFilterClick = { },
            onProfileClick = { navController.navigate("account")}
        )

        // LISTA
        if (isLoading) {
            Text("Cargando...", modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(BackgroundColor)
            ) {
                items(platillos) { platillo ->
                    PlatilloCard(
                        nombre = platillo.nombre,
                        descripcion = platillo.descripcion ?: "",
                        precio = platillo.precio,
                        imagenUrl = platillo.urlImagen ?: "",
                        onClickCard = {
                            selectedPlatillo = platillo
                            showModal = true
                        },
                        onClickAdd = {
                            selectedPlatillo = platillo
                            showModal = true
                        }
                    )
                }
            }

            if (showModal && selectedPlatillo != null) {
                PlatilloModal(
                    platillo = selectedPlatillo!!,
                    onDismiss = { showModal = false },
                    onAddToCart = { platillo, cantidad, nota ->
                        cartViewModel.addToCart(platillo, cantidad, nota)
                    }
                )
            }
        }
    }
}