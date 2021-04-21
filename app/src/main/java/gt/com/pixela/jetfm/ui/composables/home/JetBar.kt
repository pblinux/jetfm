package gt.com.pixela.jetfm.ui.composables.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gt.com.pixela.jetfm.ui.composables.common.JetTitle

@Preview(showBackground = true)
@Composable
fun JetBarPreview() {
  JetBar(elevated = false)
}


@Composable
fun JetBar(elevated: Boolean = false) {
  val elevation: Dp by animateDpAsState(if (elevated) 12.dp else 0.dp)
  val backgroundColor: Color by animateColorAsState(
    if (elevated) MaterialTheme.colors.background
    else if (isSystemInDarkTheme()) Color.Transparent else Color.White
  )

  TopAppBar(
    backgroundColor = backgroundColor,
    contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
    elevation = elevation,
  ) {
    Box(Modifier.fillMaxWidth()) {
      JetTitle(size = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
  }
}