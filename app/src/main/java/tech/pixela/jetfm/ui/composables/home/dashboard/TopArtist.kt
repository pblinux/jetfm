package tech.pixela.jetfm.ui.composables.home.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import tech.pixela.jetfm.data.model.lastfm.base.Artist
import tech.pixela.jetfm.ui.composables.items.JetColumnCard

@Composable
fun TopArtist(artist: Artist, modifier: Modifier = Modifier) {
    JetColumnCard(
        count = artist.playCount,
        imageUrl = artist.image.single { it.size == "mega" }.url,
        modifier = modifier,
        text = artist.name
    )
}
