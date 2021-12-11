package tech.pixela.jetfm.ui.screens.sections.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.home.activity.LovedTrack
import tech.pixela.jetfm.ui.composables.common.result.JetPagingColumn

@ExperimentalFoundationApi
@Composable
fun LovedTracks(
    mainViewModel: MainViewModel = hiltViewModel(),
    lazyListState: LazyListState = rememberLazyListState(),
) {
    val tracks = mainViewModel.lovedTracks.collectAsLazyPagingItems()

    JetPagingColumn(items = tracks, lazyListState) { item ->
        LovedTrack(track = item)
    }

}