package tech.pixela.jetfm.ui.composables.items

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import tech.pixela.jetfm.ui.composables.animations.JetAudioBars
import tech.pixela.jetfm.ui.composables.common.JetImage
import tech.pixela.jetfm.ui.composables.common.MediumJetSpace
import tech.pixela.jetfm.ui.composables.common.JetOverlay

@Preview
@Composable
private fun PreviewJetRowCard() {
    JetRowCard(
        song = "call the police",
        artist = "LCD Soundsystem",
        album = "Electric Lady Sessions",
        imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/d868f77dd59407d67699eb278cf235c7.jpg#d868f77dd59407d67699eb278cf235c7"
    )
}

@Preview
@Composable
private fun PreviewJetRowVariantCard() {
    JetRowVariantCard(
        text = "Electric Lady Sessions",
        headline = "Your play count: 15",
        imageUrl = "https://lastfm.freetls.fastly.net/i/u/770x0/d868f77dd59407d67699eb278cf235c7.jpg#d868f77dd59407d67699eb278cf235c7",
        width = 100.dp
    )
}

@Composable
fun JetRowCard(
    song: String,
    artist: String,
    album: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    isPlaying: Boolean = false,
) {
    Card(
        modifier = modifier.sizeIn(
            maxHeight = 200.dp,
            maxWidth = 325.dp,
            minHeight = 175.dp,
            minWidth = 175.dp,
        )
    ) {
        JetImage(url = imageUrl, modifier = Modifier.fillMaxSize())
        JetOverlay(
            color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.outline
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.BottomStart)
            ) {
                if (isPlaying) {
                    JetAudioBars(
                        width = 3.dp,
                        maxHeight = 20.dp,
                        amount = 4,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    MediumJetSpace()
                }
                Text(
                    buildAnnotatedString {
                        append(song)
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Medium)
                        ) {
                            append(" by $artist")
                        }
                    },
                    color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.inverseOnSurface,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            if (album.isNotBlank()) {
                Text(
                    buildAnnotatedString {
                        append("from ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.inverseOnSurface,
                            )
                        ) {
                            append(album)
                        }
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.inverseOnSurface,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}

@Composable
fun JetRowVariantCard(
    text: String,
    headline: String,
    imageUrl: String,
    width: Dp,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .sizeIn(
                maxWidth = width,
                minWidth = width,
                maxHeight = 200.dp,
                minHeight = 175.dp,
            )
    ) {
        JetImage(url = imageUrl, modifier = Modifier.fillMaxSize())
        JetOverlay(
            color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.outline
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .padding(16.dp)
        ) {
            Text(
                text = text,
                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.inverseOnSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = headline,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.inverseOnSurface,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun JetRowFullCard(
    text: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    headline: String? = null,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(maxHeight = 200.dp, minHeight = 175.dp)
    ) {
        JetImage(url = imageUrl, modifier = Modifier.fillMaxSize())
        JetOverlay(
            color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surface
            else MaterialTheme.colorScheme.primaryContainer
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .padding(16.dp)
        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            if (headline != null) {
                Text(
                    text = headline,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }
}