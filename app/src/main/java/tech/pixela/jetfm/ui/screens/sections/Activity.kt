package tech.pixela.jetfm.ui.screens.sections

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import tech.pixela.jetfm.data.model.utils.ActivityRoute
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.home.activity.ActivityHeader
import tech.pixela.jetfm.ui.composables.home.dashboard.ScrollUpButton
import tech.pixela.jetfm.ui.screens.sections.activities.Albums
import tech.pixela.jetfm.ui.screens.sections.activities.Artists
import tech.pixela.jetfm.ui.screens.sections.activities.LovedTracks
import tech.pixela.jetfm.ui.screens.sections.activities.Tracks

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun Activity(
    mainViewModel: MainViewModel = hiltViewModel(),
    items: List<ActivityRoute> = listOf(
        ActivityRoute.Tracks,
        ActivityRoute.Artists,
        ActivityRoute.Albums,
        ActivityRoute.LovedTracks
    )
) {
    val coroutineScope = rememberCoroutineScope()

    val albumsListState = rememberLazyListState()
    val artistsListState = rememberLazyListState()
    val lovedTracksListState = rememberLazyListState()
    val pagerState = rememberPagerState(0)
    val tracksListState = rememberLazyListState()

    Scaffold(
        topBar = {
            ActivityHeader(
                pagerState = pagerState,
                tabs = items,
                onSettingsClick = { mainViewModel.toggleDialog() }
            )
        },
        floatingActionButton = {
            ScrollUpButton(
                visible = showScrollButton(
                    route = items[pagerState.currentPage],
                    albumsLazyListState = albumsListState,
                    artistsLazyListState = artistsListState,
                    tracksLazyListState = tracksListState,
                    lovedTracksLazyListState = lovedTracksListState
                )
            ) {
                scrollUp(
                    route = items[pagerState.currentPage],
                    coroutineScope = coroutineScope,
                    albumsLazyListState = albumsListState,
                    artistsLazyListState = artistsListState,
                    tracksLazyListState = tracksListState,
                    lovedTracksLazyListState = lovedTracksListState
                )
            }
        }
    ) {
        HorizontalPager(
            pageCount = items.count(),
            state = pagerState,
            modifier = Modifier.padding(it)
        ) { page ->
            when (items[page]) {
                ActivityRoute.Tracks -> {
                    Tracks(
                        mainViewModel,
                        lazyListState = tracksListState
                    )
                }
                ActivityRoute.Artists -> {
                    Artists(
                        mainViewModel,
                        lazyListState = artistsListState
                    )
                }
                ActivityRoute.Albums -> {
                    Albums(
                        mainViewModel,
                        lazyListState = albumsListState
                    )
                }
                ActivityRoute.LovedTracks -> {
                    LovedTracks(
                        mainViewModel,
                        lazyListState = lovedTracksListState
                    )
                }
            }
        }
    }
}

fun showScrollButton(
    route: ActivityRoute,
    albumsLazyListState: LazyListState,
    artistsLazyListState: LazyListState,
    lovedTracksLazyListState: LazyListState,
    tracksLazyListState: LazyListState
): Boolean {
    return when (route) {
        ActivityRoute.Tracks -> {
            tracksLazyListState.firstVisibleItemIndex > 3
        }
        ActivityRoute.Artists -> {
            artistsLazyListState.firstVisibleItemIndex > 3
        }
        ActivityRoute.Albums -> {
            albumsLazyListState.firstVisibleItemIndex > 3
        }
        ActivityRoute.LovedTracks -> {
            lovedTracksLazyListState.firstVisibleItemIndex > 3
        }
    }
}

fun scrollUp(
    route: ActivityRoute,
    coroutineScope: CoroutineScope,
    albumsLazyListState: LazyListState,
    artistsLazyListState: LazyListState,
    lovedTracksLazyListState: LazyListState,
    tracksLazyListState: LazyListState
) {
    when (route) {
        ActivityRoute.Tracks -> {
            coroutineScope.launch { tracksLazyListState.animateScrollToItem(0) }
        }
        ActivityRoute.Artists -> {
            coroutineScope.launch { artistsLazyListState.animateScrollToItem(0) }
        }
        ActivityRoute.Albums -> {
            coroutineScope.launch { albumsLazyListState.animateScrollToItem(0) }
        }
        ActivityRoute.LovedTracks -> {
            coroutineScope.launch { lovedTracksLazyListState.animateScrollToItem(0) }
        }
    }
}
