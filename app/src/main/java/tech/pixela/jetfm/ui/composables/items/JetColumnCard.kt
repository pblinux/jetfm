package tech.pixela.jetfm.ui.composables.items

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import tech.pixela.jetfm.ui.composables.common.JetImage
import tech.pixela.jetfm.ui.composables.common.JetOverlay

@ExperimentalCoilApi
@Preview
@Composable
private fun PreviewJetColumnCard() {
    JetColumnCard(
        text = "call the police",
        count = 3,
        imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/d868f77dd59407d67699eb278cf235c7.jpg#d868f77dd59407d67699eb278cf235c7"
    )
}

@Composable
fun JetColumnCard(
    text: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    count: Int? = null
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(maxHeight = 150.dp)
    ) {
        JetImage(url = imageUrl, modifier = Modifier.fillMaxSize())
        JetOverlay(
            color = if (isSystemInDarkTheme()) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.outline
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .padding(16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.inverseOnSurface
                },
                modifier = Modifier.align(Alignment.BottomStart)
            )
            if (count != null) {
                Text(
                    text = "Your play count: $count",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = if (isSystemInDarkTheme()) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.inverseOnSurface
                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}
