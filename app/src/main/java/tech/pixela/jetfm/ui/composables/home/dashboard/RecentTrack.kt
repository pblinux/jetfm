package tech.pixela.jetfm.ui.composables.home.dashboard

import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import tech.pixela.jetfm.data.model.lastfm.base.RecentTrack
import tech.pixela.jetfm.ui.composables.items.JetRowCard

@ExperimentalCoilApi
@Composable
fun RecentTrack(recentTrack: RecentTrack) {
    JetRowCard(
        album = recentTrack.album.name,
        artist = recentTrack.artist.name,
        imageUrl = recentTrack.image.single { it.size == "extralarge" }.url,
        song = recentTrack.name,
        isPlaying = recentTrack.attributes?.isPlaying ?: false
    )
}