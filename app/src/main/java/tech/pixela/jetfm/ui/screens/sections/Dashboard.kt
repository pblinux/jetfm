package tech.pixela.jetfm.ui.screens.sections

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch
import tech.pixela.jetfm.R
import tech.pixela.jetfm.data.model.utils.JetResult
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.common.CustomJetSpace
import tech.pixela.jetfm.ui.composables.common.JetHeadline
import tech.pixela.jetfm.ui.composables.common.JetTitle
import tech.pixela.jetfm.ui.composables.common.result.withState
import tech.pixela.jetfm.ui.composables.home.dashboard.*

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun Dashboard(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val dashboardListState = rememberLazyListState()
    val homeInfo by mainViewModel.homeInfo.collectAsState()

    Scaffold(
        floatingActionButton = {
            ScrollUpButton(visible = dashboardListState.firstVisibleItemIndex > 3) {
                coroutineScope.launch { dashboardListState.animateScrollToItem(0) }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (homeInfo is JetResult.Loaded || homeInfo is JetResult.Refresh) {
                DashboardCircles(
                    visible = dashboardListState.firstVisibleItemIndex < 1,
                    modifier = Modifier.align(alignment = Alignment.TopEnd)
                )
            }

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = homeInfo is JetResult.Refresh),
                onRefresh = { mainViewModel.getHomeInfo(refresh = true) },
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = trigger,
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    state = dashboardListState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 24.dp)
                ) {
                    withState(
                        homeInfo
                    ) { data ->
                        // Title
                        item {
                            JetTitle(
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        // Recent tracks
                        item {
                            JetHeadline(
                                text = stringResource(id = R.string.recently_played),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        item {
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(
                                    data.recentTracks.recentTracks,
                                    itemContent = { RecentTrack(recentTrack = it) }
                                )
                            }
                        }

                        // Top artists
                        item {
                            JetHeadline(
                                text = stringResource(id = R.string.top_artists),
                                modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center
                            )
                        }
                        items(
                            data.topArtists.artists,
                            itemContent = {
                                TopArtist(
                                    artist = it,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                )
                            }
                        )

                        // Top tracks
                        item {
                            JetHeadline(
                                text = stringResource(id = R.string.top_tracks),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        item {
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(
                                    data.topTracks.tracks,
                                    itemContent = { TopTrack(track = it) }
                                )
                            }
                        }

                        // Top albums
                        item {
                            JetHeadline(
                                text = stringResource(id = R.string.top_albums),
                                modifier = Modifier.padding(16.dp),
                                textAlign = TextAlign.End,
                            )
                        }
                        item {
                            BoxWithConstraints {
                                FlowRow(
                                    modifier = Modifier.padding(16.dp),
                                    crossAxisSpacing = 16.dp,
                                    mainAxisSpacing = 16.dp,
                                ) {
                                    val width = (this.maxWidth / 2) - 24.dp
                                    data.topAlbums.albums.forEach {
                                        TopAlbum(
                                            album = it,
                                            width = width
                                        )
                                    }
                                }
                            }
                        }

                        item {
                            CustomJetSpace(size = 64.0)
                        }
                    }
                }
            }
        }
    }
}