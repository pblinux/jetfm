package gt.com.pixela.jetfm.ui.composables.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gt.com.pixela.jetfm.ui.composables.common.JetTitle

@Preview
@Composable
fun JetBar() {
  TopAppBar(
    backgroundColor = Color.Transparent,
    elevation = 0.dp,
    contentPadding = PaddingValues(top = 24.dp, bottom = 4.dp)
  ) {
    Box(
      modifier = Modifier.fillMaxWidth()
    ) {
      JetTitle(size = 32.sp, modifier = Modifier.align(Alignment.Center))
    }
  }
}