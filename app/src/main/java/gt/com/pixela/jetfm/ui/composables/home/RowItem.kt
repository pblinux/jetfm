package gt.com.pixela.jetfm.ui.composables.home

import androidx.compose.foundation.Image
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
import com.google.accompanist.coil.rememberCoilPainter
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.data.models.Album
import gt.com.pixela.jetfm.data.models.RecentTrack
import gt.com.pixela.jetfm.data.models.Track
import gt.com.pixela.jetfm.ui.composables.animations.JetAudioBars

@Preview
@Composable
fun FavTrackItemPreview() {
  RoundedRowItemRaw(
    imageUrl = "https://lastfm.freetls.fastly.net/i/u/174s/043311d565be4296bb13f299ba1f08de.jpg",
    title = "Unknown Pleasures",
    subtitle = "10 Apr 2018, 23:10",
  )
}

@Composable
fun FavTrackItem(track: Track, size: Dp = 200.dp) {
  RoundedRowItemRaw(
    imageUrl = track.image.single { it.size == "extralarge" }.url,
    title = track.name,
    subtitle = track.date!!.date,
    size = size
  )
}

@Preview
@Composable
fun AlbumRowPreview() {
  RoundedRowItemRaw(
    imageUrl = "https://lastfm.freetls.fastly.net/i/u/174s/043311d565be4296bb13f299ba1f08de.jpg",
    title = "Unknown Pleasures by Joy Division",
    subtitle = "Your play count: 29",
  )
}

@Composable
fun AlbumRowItem(album: Album, size: Dp = 200.dp) {
  RoundedRowItemRaw(
    imageUrl = album.image.single { it.size == "extralarge" }.url,
    title = "${album.name} by ${album.artist.name}",
    subtitle = "Your play count: ${album.playCount}",
    size = size
  )
}

@Preview
@Composable
private fun TrackRowPreview() {
  RowItemRaw(
    imageUrl = "https://lastfm.freetls.fastly.net/i/u/174s/043311d565be4296bb13f299ba1f08de.jpg",
    name = "Love will tear us apart",
    artist = "Joy Division",
    album = "Unknown Pleasures"
  )
}

@Composable
fun TrackRowItem(track: Track) {
  RowItemRaw(
    imageUrl = track.image.single { it.size == "extralarge" }.url,
    name = track.name,
    artist = track.artist.name,
    album = "",
    isPlaying = false
  )
}

@Composable
fun RecentTrackRowItem(recentTrack: RecentTrack) {
  val isPlaying =
    if (recentTrack.attributes != null) recentTrack.attributes.isPlaying else false

  RowItemRaw(
    imageUrl = recentTrack.image.single { it.size == "extralarge" }.url,
    name = recentTrack.name,
    artist = recentTrack.artist.name,
    album = recentTrack.album.name,
    isPlaying = isPlaying
  )
}

@Composable
fun RoundedRowItemRaw(
  imageUrl: String,
  title: String,
  subtitle: String,
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
    Image(
      painter = rememberCoilPainter(
        imageUrl,
        fadeIn = true,
        previewPlaceholder = R.drawable.ic_launcher_background
      ),
      contentDescription = title,
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
      text = title,
      color = Color.White,
      style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp),
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(horizontal = 16.dp, vertical = 24.dp)
    )
    Text(
      text = subtitle,
      color = Color.White,
      style = MaterialTheme.typography.caption.copy(fontSize = 8.sp),
      modifier = Modifier
        .align(Alignment.TopEnd)
        .padding(horizontal = 16.dp, vertical = 24.dp)
    )
  }
}

@Composable
private fun RowItemRaw(
  imageUrl: String,
  name: String,
  artist: String,
  album: String,
  isPlaying: Boolean = false,
) {
  val dark = isSystemInDarkTheme()

  Box(
    modifier = Modifier
      .width(275.dp)
      .height(175.dp)
      .clip(RoundedCornerShape(16.dp))
      .background(color = if (dark) Color.Black else Color.White)
  ) {
    Image(
      painter = rememberCoilPainter(
        imageUrl,
        fadeIn = true,
        previewPlaceholder = R.drawable.ic_launcher_background
      ),
      contentDescription = name,
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
    Row(
      Modifier
        .align(Alignment.BottomStart)
        .padding(horizontal = 16.dp, vertical = 24.dp),
      verticalAlignment = Alignment.Bottom
    ) {
      if (isPlaying)
        JetAudioBars(width = 3.dp, maxHeight = 20.dp, amount = 4)
      if (isPlaying)
        Spacer(Modifier.width(12.dp))
      Text(
        text = "$name by $artist",
        color = Color.White,
        style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp),
      )
    }
    if (album.isNotBlank())
      Text(
        text = "from $album",
        color = Color.White,
        style = MaterialTheme.typography.caption.copy(fontSize = 8.sp),
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(horizontal = 16.dp, vertical = 24.dp)
      )
  }
}
