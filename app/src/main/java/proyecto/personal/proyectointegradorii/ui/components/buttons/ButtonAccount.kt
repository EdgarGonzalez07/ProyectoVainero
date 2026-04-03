package proyecto.personal.proyectointegradorii.ui.components.buttons

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import proyecto.personal.proyectointegradorii.ui.components.texts.GlobalText
import proyecto.personal.proyectointegradorii.ui.theme.AlertColor
import proyecto.personal.proyectointegradorii.ui.theme.MainColor
import proyecto.personal.proyectointegradorii.ui.theme.TextColorDark

@Composable
fun ButtonAccount(
    icon: ImageVector,
    text: String,
    isDesctructive: Boolean = false,
    onClick: () -> Unit,
) {
    val tintColor = if (isDesctructive) AlertColor else MainColor
    val containerColor = tintColor.copy(alpha = 0.15f)
    val textColor = if (isDesctructive) AlertColor else TextColorDark

    Row(
       modifier = Modifier
           .fillMaxWidth()
           .clickable { onClick() }
           .padding(vertical = 16.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(containerColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = tintColor
            )
        }
        Spacer(
            Modifier
                .width(15.dp)
        )
        GlobalText(
            text,
            16,
            textColor,
            Modifier.weight(1f)
        )
        Spacer(
            Modifier
                .padding(horizontal = 10.dp)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Ir",
            tint = Color.Gray
        )
    }
}