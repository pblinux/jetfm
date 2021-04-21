package gt.com.pixela.jetfm.ui.composables.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import gt.com.pixela.jetfm.ui.theme.secondaryRed
import gt.com.pixela.jetfm.ui.theme.white
import java.util.*

@Preview
@Composable
fun PreviewJetButton() {
  JetButton(title = "Test", isLoading = true, onClick = {})
}

@Composable
fun JetButton(
  title: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  isLoading: Boolean = false,
) {
  Button(
    modifier = modifier.animateContentSize(),
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
      backgroundColor = secondaryRed
    ),
  ) {
    if (isLoading)
      CircularProgressIndicator(color = white)
    else
      Text(
        text = title.toUpperCase(Locale.getDefault()),
        style = MaterialTheme.typography.button,
        color = white,
        modifier = Modifier.padding(8.dp)
      )
  }
}