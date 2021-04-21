package gt.com.pixela.jetfm.ui.composables.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun UninitializedView() {
  Box(Modifier.fillMaxSize()) {
    Text("No he iniciado", Modifier.align(Alignment.Center), textAlign = TextAlign.Center)
  }
}

@Preview
@Composable
fun LoadingView() {
  Box(Modifier.fillMaxSize()) {
    CircularProgressIndicator(Modifier.align(Alignment.Center))
  }
}

@Preview
@Composable
fun ErrorViewPreview() {
  ErrorView {
    //TO-DO
  }
}

@Composable
fun ErrorView(
  onRetry: () -> Unit
) {
  Box(Modifier.fillMaxSize()) {
    Card(Modifier.align(Alignment.Center)) {
      Column(Modifier.padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Something happens :(")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRetry) {
          Text("Try again")
        }
      }
    }
  }
}