package tech.pixela.jetfm.ui.composables.home.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import soup.compose.material.motion.animation.materialFadeIn
import soup.compose.material.motion.animation.materialFadeOut

@ExperimentalAnimationApi
@Composable
fun ScrollUpButton(
    visible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = materialFadeIn(),
        exit = materialFadeOut()
    ) {
        FloatingActionButton(
            onClick = onClick
        ) { Icon(Icons.Filled.KeyboardArrowUp, null) }
    }
}
