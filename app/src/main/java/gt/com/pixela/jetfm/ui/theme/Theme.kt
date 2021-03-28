@file:Suppress("SpellCheckingInspection")

package gt.com.pixela.jetfm.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = primaryRed,
    primaryVariant = secondaryRed,
    secondary = primaryBlue,
    secondaryVariant = secondaryBlue,
)

private val LightColorPalette = lightColors(
    primary = primaryRed,
    primaryVariant = secondaryRed,
    secondary = primaryBlue,
    secondaryVariant = secondaryBlue,
)

@Composable
fun JetfmTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}