package proyecto.personal.proyectointegradorii.ui.components.headers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import proyecto.personal.proyectointegradorii.ui.components.bars.AppSearchBar
import proyecto.personal.proyectointegradorii.ui.components.buttons.icons.IconNavigateButton
import proyecto.personal.proyectointegradorii.ui.theme.BackgroundCardColor
import proyecto.personal.proyectointegradorii.ui.theme.MainColor

@Composable
fun HeaderHome(
    query: String,
    onQueryChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp), // Margen general
        verticalAlignment = Alignment.CenterVertically
    ) {
        // --- BOTÓN DE FILTROS ---
        IconNavigateButton(
            icon = Icons.Rounded.Tune, // O el icono de filtros que prefieras
            contentDescription = "Filtros",
            onClick = onFilterClick,
            size = 56, // Misma altura
            containerColor = BackgroundCardColor,
            iconColor = MainColor,
            elevation = 4
        )

        Spacer(modifier = Modifier.width(12.dp))

        // --- BARRA DE BÚSQUEDA ---
        AppSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        // --- BOTÓN DE PERFIL ---
        IconNavigateButton(
            icon = Icons.Outlined.Person, // O el icono de usuario que prefieras
            contentDescription = "Perfil",
            onClick = onProfileClick,
            size = 56, // Misma altura
            containerColor = BackgroundCardColor,
            iconColor = MainColor,
            elevation = 4
        )
    }
}