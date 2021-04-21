package gt.com.pixela.jetfm.ui.composables.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.CoilImage
import gt.com.pixela.jetfm.data.models.Album

@Preview
@Composable
fun AlbumRowPreview() {
  AlbumRowItemRaw(
    imageUrl = "https://lastfm.freetls.fastly.net/i/u/174s/043311d565be4296bb13f299ba1f08de.jpg",
    name = "Unknown Pleasures",
    artist = "Joy Division",
    playCount = 29,
  )
}

@Composable
fun AlbumRowItem(album: Album, size: Dp = 200.dp) {
  AlbumRowItemRaw(
    imageUrl = album.image.single { it.size == "large" }.url,
    name = album.name,
    artist = album.artist.name,
    playCount = album.playCount,
    size = size
  )
}

@Composable
fun AlbumRowItemRaw(
  imageUrl: String,
  name: String,
  artist: String,
  playCount: Int,
  size: Dp = 200.dp,
) {
  val dark = isSystemInDarkTheme()

  Box(
    modifier = Modifier
      .width(size)
      .height(size)
      .clip(RoundedCornerShape(16.dp))
      .background(color = if (dark) Color.Black else Color.White)
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
      text = "$name by $artist",
      color = Color.White,
      style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp),
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(horizontal = 16.dp, vertical = 24.dp)
    )
    Text(
      text = "Your play count: $playCount",
      color = Color.White,
      style = MaterialTheme.typography.caption.copy(fontSize = 8.sp),
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(horizontal = 16.dp, vertical = 24.dp)
    )
  }
}
