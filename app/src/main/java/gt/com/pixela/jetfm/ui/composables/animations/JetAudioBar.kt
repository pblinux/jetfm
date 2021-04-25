package gt.com.pixela.jetfm.ui.composables.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import kotlin.random.Random

@Preview
@Composable
private fun JetAudioBarPreview() {
  JetAudioBars(width = 30.dp, maxHeight = 150.dp, amount = 4)
}


@Composable
fun JetAudioBars(
  width: Dp, maxHeight: Dp,
  amount: Int = 3, color: Color = Color.Green, spacing: Dp = 2.dp,
) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(spacing),
    verticalAlignment = Alignment.Bottom,
    modifier = Modifier.sizeIn(maxHeight = maxHeight, minHeight = maxHeight)
  ) {
    repeat(amount) {
      JetAudioBarAnimated(width = width, maxHeight = maxHeight, color = color)
    }
  }
}

@Composable
private fun JetAudioBarAnimated(width: Dp, maxHeight: Dp, color: Color = Color.Green) {
  val duration by remember { mutableStateOf(Random.nextInt(600, 800)) }
  val delay by remember { mutableStateOf(Random.nextInt(200, 400)) }
  val infiniteTransition = rememberInfiniteTransition()
  val animation by infiniteTransition.animateFloat(
    initialValue = 0.10f,
    targetValue = 0.10f,
    animationSpec = infiniteRepeatable(
      repeatMode = RepeatMode.Restart,
      animation = keyframes {
        durationMillis = duration
        delayMillis = delay
        0.50f at durationMillis.times(0.3f).roundToInt() with FastOutLinearInEasing
        1.00f at durationMillis.times(0.6f).roundToInt() with FastOutLinearInEasing
        0.10f at durationMillis with FastOutLinearInEasing
      }
    )
  )

  JetAudioBarWithShape(
    color = color,
    width = width,
    height = maxHeight.times(animation)
  )
}

@Composable
private fun JetAudioBarWithCanvas(width: Dp, height: Dp, color: Color) {
  Canvas(Modifier.size(width, height)) {
    drawRect(
      color = color,
      size = Size(
        height = size.height,
        width = size.width
      )
    )
  }
}

@Composable
private fun JetAudioBarWithShape(width: Dp, height: Dp, color: Color) {
  Box(
    Modifier
      .size(width, height)
      .clip(RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
      .background(color)
  )
}