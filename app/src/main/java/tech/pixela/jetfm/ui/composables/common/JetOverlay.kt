package tech.pixela.jetfm.ui.composables.common

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

@Composable
fun JetOverlay(
    isDark: Boolean = isSystemInDarkTheme(),
    color: Color = Color.Black,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(if (isDark) 0.6f else 0.65f)
            .background(color),
        content = {}
    )
}