package gt.com.pixela.jetfm.ui.composables.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.ui.theme.primaryRed
import gt.com.pixela.jetfm.ui.theme.white

@Preview
@Composable
fun JetTitle(modifier: Modifier = Modifier, size: TextUnit = 51.sp) {
  Text(
    color = primaryRed,
    fontSize = size,
    fontWeight = FontWeight.SemiBold,
    modifier = modifier,
    style = MaterialTheme.typography.h3,
    text = stringResource(id = R.string.title),
  )
}

@Preview
@Composable
fun JetLine() {
  Text(
    text = stringResource(id = R.string.tagline),
    color = white,
    style = MaterialTheme.typography.body1,
    textAlign = TextAlign.Center
  )
}