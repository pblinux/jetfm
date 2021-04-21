package gt.com.pixela.jetfm.ui.composables.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
    verticalAlignment = Alignment.Bottom,
    modifier = Modifier.sizeIn(maxHeight = maxHeight, minHeight = maxHeight)
  ) {
    repeat(amount) {
      val percentage by remember { mutableStateOf(Random.nextFloat()) }
      val targetPercentage by remember { mutableStateOf(Random.nextFloat()) }
      val animationDuration by remember { mutableStateOf(Random.nextInt(600, 1200)) }
      val infiniteTransition = rememberInfiniteTransition()

      val animation by infiniteTransition.animateValue(
        initialValue = maxHeight.times(percentage),
        targetValue = maxHeight.times(targetPercentage),
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
          animation = tween(durationMillis = animationDuration),
          repeatMode = RepeatMode.Reverse
        )
      )

      JetAudioBarWithCanvas(
        color = color,
        width = width,
        height = animation
      )
      if (it < amount - 1) Spacer(modifier = Modifier.width(spacing))
    }
  }
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
      .clip(RectangleShape)
      .background(color)
  )
}