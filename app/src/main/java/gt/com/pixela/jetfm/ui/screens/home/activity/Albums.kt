package gt.com.pixela.jetfm.ui.screens.home.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import gt.com.pixela.jetfm.data.models.Album
import gt.com.pixela.jetfm.ui.composables.common.LoadingItem
import gt.com.pixela.jetfm.ui.composables.common.LoadingView
import gt.com.pixela.jetfm.ui.composables.home.HistoryAlbumItem
import gt.com.pixela.jetfm.utils.LocalHomeViewModel

@Composable
fun Albums() {
  val homeViewModel = LocalHomeViewModel.current
  val albums: LazyPagingItems<Album> = homeViewModel.albums.collectAsLazyPagingItems()
  val period by homeViewModel.period.collectAsState()
  LaunchedEffect(period) { homeViewModel.reloadAlbums() }

  LazyColumn(
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    items(albums, itemContent = {
      HistoryAlbumItem(album = it!!)
    })

    albums.apply {
      when {
        loadState.refresh is LoadState.Loading -> {
          item { LoadingView(Modifier.fillParentMaxSize()) }
        }
        loadState.append is LoadState.Loading -> {
          item { LoadingItem(Modifier.fillParentMaxWidth()) }
        }
      }
    }

    item { Spacer(Modifier.height(64.dp)) }
  }
}