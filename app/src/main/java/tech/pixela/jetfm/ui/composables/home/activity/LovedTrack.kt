package tech.pixela.jetfm.ui.composables.home.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.pixela.jetfm.data.model.lastfm.base.Track
import tech.pixela.jetfm.ui.composables.items.JetRowFullCard

@Composable
fun LovedTrack(track: Track, modifier: Modifier = Modifier) {
    JetRowFullCard(
        text = track.name,
        imageUrl = track.image.single { it.size == "extralarge" }.url,
        modifier = modifier
    )
}