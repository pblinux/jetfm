package tech.pixela.jetfm.ui.composables.home.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import tech.pixela.jetfm.data.model.lastfm.base.Album
import tech.pixela.jetfm.ui.composables.items.JetRowVariantCard

@Composable
fun TopAlbum(album: Album, width: Dp) {
    JetRowVariantCard(
        text = album.name,
        headline = "Your play count: ${album.playCount}",
        imageUrl = album.image.single { it.size == "extralarge" }.url,
        width = width
    )
}