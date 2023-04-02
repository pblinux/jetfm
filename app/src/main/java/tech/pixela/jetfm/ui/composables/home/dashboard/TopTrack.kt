package tech.pixela.jetfm.ui.composables.home.dashboard

import androidx.compose.runtime.Composable
import tech.pixela.jetfm.data.model.lastfm.base.Track
import tech.pixela.jetfm.ui.composables.items.JetRowCard

@Composable
fun TopTrack(track: Track) {
    JetRowCard(
        album = "",
        artist = track.artist.name,
        imageUrl = track.image.single { it.size == "extralarge" }.url,
        isPlaying = false,
        song = track.name
    )
}
