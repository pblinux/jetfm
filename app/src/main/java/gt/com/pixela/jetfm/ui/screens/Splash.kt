package gt.com.pixela.jetfm.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import gt.com.pixela.jetfm.ui.theme.JetfmTheme
import gt.com.pixela.jetfm.ui.theme.primaryRed

@Preview
@Composable
fun Splash() {
  JetfmTheme() {
    Surface(color = primaryRed, modifier = Modifier.fillMaxSize()) {
    }
  }
}

