package tech.pixela.jetfm.ui.composables.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import tech.pixela.jetfm.R

@Preview
@Composable
fun JetLine() {
    Text(
        text = stringResource(id = R.string.tagline),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )
}
