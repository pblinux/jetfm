package gt.com.pixela.jetfm.ui.composables.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
private fun Gap(size: Double) {
  Spacer(
    modifier = Modifier
      .height(size.dp)
      .width(size.dp)
  )
}

@Composable
fun SmallGap() {
  Gap(size = 8.0)
}

@Composable
fun MediumGap() {
  Gap(size = 14.0)
}

@Composable
fun LargeGap() {
  Gap(size = 16.0)
}

@Composable
fun CustomGap(size: Double) {
  Gap(size)
}