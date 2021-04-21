package gt.com.pixela.jetfm.ui.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.CoilImage
import gt.com.pixela.jetfm.data.models.Artist

@Preview
@Composable
fun ColumnArtistItemPreview() {
  ColumnArtistItemRaw(
    imageUrl = "https://lastfm.freetls.fastly.net/i/u/300x300/2a96cbd8b46e442fc41c2b86b821562f.png",
    name =  "Massive Attack",
    count = 17,
  )
}

@Composable
fun ArtistColumnItem(artist: Artist) {
  ColumnArtistItemRaw(
    imageUrl = artist.image.single { it.size == "mega" }.url,
    name = artist.name,
    count = artist.playCount
  )
}

@Composable
private fun ColumnArtistItemRaw(
  imageUrl: String,
  name: String,
  count: Int,
) {
  val dark = isSystemInDarkTheme()

  Box(
    Modifier
      .fillMaxWidth()
      .height(150.dp)
  ) {
    CoilImage(
      data = imageUrl,
      contentDescription = name,
      fadeIn = true,
      contentScale = ContentScale.Crop,
      modifier = Modifier.fillMaxSize()
    )
    Box(
      modifier = Modifier
        .fillMaxSize()
        .alpha(if (dark) 0.6f else 0.5f)
        .background(Color.Black),
      content = {}
    )
    Text(
      text = name,
      color = Color.White,
      style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp),
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(horizontal = 16.dp, vertical = 24.dp)
    )
    Text(
      text = "Your play count: $count",
      color = Color.White,
      style = MaterialTheme.typography.caption.copy(fontSize = 8.sp),
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(horizontal = 16.dp, vertical = 24.dp)
    )
  }
}