package gt.com.pixela.jetfm.ui.composables.home

import android.text.Layout
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.CoilImage
import gt.com.pixela.jetfm.data.models.Album
import gt.com.pixela.jetfm.data.models.Artist
import gt.com.pixela.jetfm.data.models.Track

@Preview
@Composable
private fun HistoryTrackPreview() {
  HistoryItemRaw(
    imageUrl = "https://lastfm.freetls.fastly.net/i/u/174s/043311d565be4296bb13f299ba1f08de.jpg",
    title = "Joy Division",
    caption = "from Unknown Pleasures",
    amount = "546"
  )
}

@Composable
fun HistoryArtistItem(artist: Artist) {
  HistoryItemRaw(
    imageUrl = artist.image.single { it.size == "large" }.url,
    title = artist.name,
    caption = "",
    amount = "${artist.playCount}"
  )
}

@Composable
fun HistoryAlbumItem(album: Album) {
  HistoryItemRaw(
    imageUrl = album.image.single { it.size == "large" }.url,
    title = album.name,
    caption = "by ${album.artist.name}",
    amount = "${album.playCount}"
  )
}

@Composable
fun HistoryTrackItem(track: Track) {
  HistoryItemRaw(
    imageUrl = track.image.single { it.size == "large" }.url,
    title = track.name,
    caption = "by ${track.artist.name}",
    amount = "${track.playCount}"
  )
}

@Composable
fun LovedTrackItem(track: Track) {
  HistoryItemRaw(
    imageUrl = track.image.single { it.size == "large" }.url,
    title = track.name,
    caption = track.date!!.date,
    amount = ""
  )
}

@Composable
private fun HistoryItemRaw(
  imageUrl: String,
  title: String,
  caption: String,
  amount: String,
) {
  val dark = isSystemInDarkTheme()

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(175.dp)
      .clip(RoundedCornerShape(16.dp))
      .background(color = if (dark) Color.Black else Color.White)
  ) {
    CoilImage(
      data = imageUrl,
      contentDescription = title,
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
    Row(
      Modifier
        .align(Alignment.BottomStart)
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 24.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = title,
        color = Color.White,
        style = MaterialTheme.typography.subtitle1.copy(fontSize = 16.sp),
        modifier = Modifier.weight(4f)
      )
      if (amount.isNotBlank())
        Spacer(modifier = Modifier.width(16.dp))
      if (amount.isNotBlank())
        Text(
          text = amount,
          textAlign = TextAlign.End,
          color = Color.White,
          style = MaterialTheme.typography.h5,
          modifier = Modifier.weight(1f)
        )
    }
    if (caption.isNotBlank())
      Text(
        text = caption,
        color = Color.White,
        style = MaterialTheme.typography.caption.copy(fontSize = 8.sp),
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(horizontal = 16.dp, vertical = 24.dp)
      )
  }
}