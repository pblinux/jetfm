package tech.pixela.jetfm.ui.screens.sections.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.common.result.JetPagingColumn
import tech.pixela.jetfm.ui.composables.home.activity.Artist

@ExperimentalFoundationApi
@Composable
fun Artists(
    mainViewModel: MainViewModel = hiltViewModel(),
    lazyListState: LazyListState = rememberLazyListState()
) {
    val artists = mainViewModel.artists.collectAsLazyPagingItems()

    JetPagingColumn(items = artists, lazyListState) { item ->
        Artist(artist = item)
    }
}
