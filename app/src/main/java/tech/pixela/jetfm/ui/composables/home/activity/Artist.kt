package tech.pixela.jetfm.ui.composables.home.activity

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.pixela.jetfm.data.model.lastfm.base.Artist
import tech.pixela.jetfm.ui.composables.items.JetRowFullCard

@Composable
fun Artist(artist: Artist, modifier: Modifier = Modifier) {
    JetRowFullCard(
        text = artist.name,
        headline = "Your play count: ${artist.playCount}",
        imageUrl = artist.image.single { it.size == "extralarge" }.url,
        modifier = modifier
    )
}