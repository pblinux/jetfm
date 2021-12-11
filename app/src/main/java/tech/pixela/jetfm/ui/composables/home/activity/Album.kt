package tech.pixela.jetfm.ui.composables.home.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.pixela.jetfm.data.model.lastfm.base.Album
import tech.pixela.jetfm.ui.composables.items.JetRowFullCard

@Composable
fun Album(album: Album, modifier: Modifier = Modifier) {
    JetRowFullCard(
        text = album.name,
        headline = "Your play count: ${album.playCount}",
        imageUrl = album.image.single { it.size == "extralarge" }.url,
        modifier = modifier
    )
}