package gt.com.pixela.jetfm.ui.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import gt.com.pixela.jetfm.data.models.RecentTracks
import gt.com.pixela.jetfm.data.models.Track
import gt.com.pixela.jetfm.data.vm.ResultState
import gt.com.pixela.jetfm.utils.LocalHomeViewModel

@ExperimentalFoundationApi
@Composable
fun Dashboard() {
  val homeViewModel = LocalHomeViewModel.current
  val tracksState by homeViewModel.weeklyTracks.collectAsState()

  when (tracksState) {
    ResultState.Uninitialized -> {
      Text(text = "No he iniciado")
    }
    ResultState.Loading -> {
      Text(text = "Estoy cargando")
    }
    ResultState.Error -> {
      Text(text = "Soy un error")
    }
    is ResultState.Loaded -> {
      LazyColumn() {
        items((15), itemContent = { Text(it.toString()) })
        item {
          Column {
            repeat((1..20).count()) { Text("Probando") }
          }
        }
        items((15), itemContent = { Text(it.toString()) })
      }


//      val tracks = (tracksState as ResultState.Loaded).data as RecentTracks
//      LazyVerticalGrid(cells = GridCells.Fixed(2), Modifier.padding(bottom = 32.dp)) {
//        items((tracks.tracks), itemContent = {
//          TrackGridItem(track = it)
//        })
    }
  }
}

@Composable
fun TrackGridItem(track: Track) {
  Box(
    modifier = Modifier
      .background(Color.Red)
      .fillMaxWidth()
      .height(200.dp)
  ) {
    CoilImage(
      data = track.image.single { it.size == "large" }.url,
      contentDescription = track.name,
      fadeIn = true,
      contentScale = ContentScale.FillBounds
    )

    Box(
      modifier = Modifier
        .fillMaxSize()
        .alpha(0.5f)
        .background(Color.Black)
    ) {

    }

    Text(track.name)
  }
}