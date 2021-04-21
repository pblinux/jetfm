package gt.com.pixela.jetfm.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import gt.com.pixela.jetfm.data.models.HomeInfo
import gt.com.pixela.jetfm.data.vm.ResultState
import gt.com.pixela.jetfm.ui.composables.common.ErrorView
import gt.com.pixela.jetfm.ui.composables.common.LoadingView
import gt.com.pixela.jetfm.ui.composables.common.UninitializedView
import gt.com.pixela.jetfm.ui.composables.home.AlbumRowItem
import gt.com.pixela.jetfm.ui.composables.home.ArtistColumnItem
import gt.com.pixela.jetfm.ui.composables.home.RecentTrackRowItem
import gt.com.pixela.jetfm.ui.composables.home.TrackRowItem
import gt.com.pixela.jetfm.utils.LocalHomeViewModel

@ExperimentalFoundationApi
@Composable
fun Dashboard() {
  val homeViewModel = LocalHomeViewModel.current
  val homeState by homeViewModel.home.collectAsState()
  val scrollState = rememberLazyListState()
  homeViewModel.updateBarElevation(scrollState.firstVisibleItemIndex)

  when (homeState) {
    ResultState.Uninitialized -> UninitializedView()
    ResultState.Loading -> LoadingView()
    ResultState.Error -> ErrorView {}
    is ResultState.Loaded -> {
      val loadedData = (homeState as ResultState.Loaded).data as HomeInfo

      LazyColumn(
        state = scrollState,
      ) {
        // Recent tracks
        item {
          Text(
            "Recently played tracks",
            Modifier
              .padding(horizontal = 16.dp)
              .padding(top = 8.dp),
            style = MaterialTheme.typography.h6,
          )
        }
        item {
          LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            items(
              loadedData.recentTracks.recentTracks,
              itemContent = { RecentTrackRowItem(recentTrack = it) })
          }
        }
        // Top artists
        item {
          Text(
            "Your top artists for the week",
            Modifier
              .padding(horizontal = 16.dp)
              .padding(bottom = 16.dp),
            style = MaterialTheme.typography.h6,
          )
        }
        items(
          loadedData.topArtists.artists,
          itemContent = { ArtistColumnItem(artist = it) }
        )
        // Top tracks
        item {
          Text(
            "Top tracks for this week",
            Modifier
              .padding(horizontal = 16.dp)
              .padding(bottom = 16.dp, top = 16.dp),
            style = MaterialTheme.typography.h6,
          )
        }
        item {
          LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            items(
              loadedData.topTracks.tracks,
              itemContent = { TrackRowItem(track = it) })
          }
        }
        // Top albums
        item {
          Text(
            "Your weekly's top albums",
            Modifier
              .padding(horizontal = 16.dp, vertical = 16.dp),
            style = MaterialTheme.typography.h6,
          )
        }
        item {
          BoxWithConstraints() {
            FlowRow(
              Modifier
                .padding(horizontal = 16.dp),
              crossAxisSpacing = 16.dp,
              mainAxisSpacing = 12.dp,
            ) {
              val size = (this.maxWidth / 2) - 24.dp

              loadedData.topAlbums.albums.forEach {
                AlbumRowItem(album = it, size = size)
              }
            }
          }
        }
        item {
          Spacer(Modifier.height(64.dp))
        }
      }
    }
  }
}