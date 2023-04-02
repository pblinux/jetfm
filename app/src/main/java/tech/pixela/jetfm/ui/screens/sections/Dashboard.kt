package tech.pixela.jetfm.ui.screens.sections

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.launch
import tech.pixela.jetfm.R
import tech.pixela.jetfm.data.model.utils.JetResult
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.common.CustomJetSpace
import tech.pixela.jetfm.ui.composables.common.JetHeadline
import tech.pixela.jetfm.ui.composables.common.JetTitle
import tech.pixela.jetfm.ui.composables.common.result.withState
import tech.pixela.jetfm.ui.composables.home.dashboard.*

@OptIn(ExperimentalMaterialApi::class)
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
    val firstVisibleItemIndex by remember {
        derivedStateOf { dashboardListState.firstVisibleItemIndex }
    }
    val refreshState = rememberPullRefreshState(
        refreshing = homeInfo is JetResult.Refresh,
        { mainViewModel.getHomeInfo(refresh = true) }
    )

    Scaffold(floatingActionButton = {
        ScrollUpButton(visible = firstVisibleItemIndex > 3) {
            coroutineScope.launch { dashboardListState.animateScrollToItem(0) }
        }
    }) { it ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .pullRefresh(state = refreshState, enabled = true)
        ) {
            if (homeInfo is JetResult.Loaded || homeInfo is JetResult.Refresh) {
                DashboardCircles(
                    visible = firstVisibleItemIndex < 1,
                    modifier = Modifier.align(alignment = Alignment.TopEnd)
                )
            }

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
                            modifier = Modifier.fillMaxWidth()
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
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                    items(data.topArtists.artists, itemContent = { artist ->
                        TopArtist(
                            artist = artist,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    })

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
                            items(data.topTracks.tracks, itemContent = { TopTrack(track = it) })
                        }
                    }

                    // Top albums
                    item {
                        JetHeadline(
                            text = stringResource(id = R.string.top_albums),
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.End
                        )
                    }
                    item {
                        BoxWithConstraints {
                            FlowRow(
                                modifier = Modifier.padding(16.dp),
                                crossAxisSpacing = 16.dp,
                                mainAxisSpacing = 16.dp
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

            PullRefreshIndicator(
                refreshing = homeInfo is JetResult.Refresh,
                state = refreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}
