package tech.pixela.jetfm.ui.composables.home.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import soup.compose.material.motion.animation.materialFadeIn
import soup.compose.material.motion.animation.materialFadeOut
import tech.pixela.jetfm.ui.composables.animations.JetCircles

@ExperimentalAnimationApi
@Composable
fun DashboardCircles(
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier
            .offset(100.dp, (-25).dp),
        enter = materialFadeIn(),
        exit = materialFadeOut()
    ) {
        JetCircles(
            size = 250,
            stroke = 2f,
            modifier = Modifier
                .alpha(0.25f)
        )
    }
}
