package tech.pixela.jetfm.ui.screens.sections.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.common.result.JetPagingColumn
import tech.pixela.jetfm.ui.composables.home.activity.Track

@ExperimentalFoundationApi
@Composable
fun Tracks(
    mainViewModel: MainViewModel = hiltViewModel(),
    lazyListState: LazyListState = rememberLazyListState()
) {
    val tracks = mainViewModel.tracks.collectAsLazyPagingItems()

    JetPagingColumn(items = tracks, lazyListState) { item ->
        Track(track = item)
    }
}
