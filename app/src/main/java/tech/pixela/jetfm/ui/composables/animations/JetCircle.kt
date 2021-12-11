package tech.pixela.jetfm.ui.composables.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview()
@Composable
fun PreviewJetCircles() {
    JetCircles(modifier = Modifier.alpha(0.5f), size = 200)
}


@Composable
fun JetCircles(size: Int, modifier: Modifier, stroke: Float = 1f) {
    val infiniteTransition = rememberInfiniteTransition()

    val firstRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 365f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 5000
            },
            repeatMode = RepeatMode.Restart
        )
    )

    val secondRotation by infiniteTransition.animateFloat(
        initialValue = 365f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 10000
            },
            repeatMode = RepeatMode.Reverse
        )
    )

    val thirdRotation by infiniteTransition.animateFloat(
        initialValue = 365f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 7000
            },
            repeatMode = RepeatMode.Restart
        )
    )

    Box(modifier) {
        JetCircle(
            stroke = stroke,
            modifier = Modifier
                .size(size.dp)
                .align(Alignment.Center)
                .rotate(firstRotation)
        )
        JetCircle(
            stroke = stroke,
            modifier = Modifier
                .size((size * 0.95).dp)
                .align(Alignment.Center)
                .rotate(secondRotation)
        )
        JetCircle(
            stroke = stroke,
            modifier = Modifier
                .size((size * 0.9).dp)
                .align(Alignment.Center)
                .rotate(thirdRotation)
        )
    }
}


@Composable
fun JetCircle(modifier: Modifier = Modifier, stroke: Float = 1f) {
    val colors = listOf(
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
    )

    Canvas(
        modifier = modifier,
        onDraw = {
            drawCircle(
                brush = Brush.linearGradient(colors),
                style = Stroke(
                    width = stroke
                ),
            )
        },
    )
}