package gt.com.pixela.jetfm.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import gt.com.pixela.jetfm.ui.screens.home.activity.Albums
import gt.com.pixela.jetfm.ui.screens.home.activity.Artists
import gt.com.pixela.jetfm.ui.screens.home.activity.LovedTracks
import gt.com.pixela.jetfm.ui.screens.home.activity.Tracks
import kotlinx.coroutines.launch

sealed class ActivityType(val title: String) {
  object TracksActivity : ActivityType("Tracks")
  object ArtistsActivity : ActivityType("Artists")
  object AlbumsActivity : ActivityType("Albums")
  object LovedActivity : ActivityType("Loved tracks")
}

val Activities = listOf(
  ActivityType.TracksActivity,
  ActivityType.ArtistsActivity,
  ActivityType.AlbumsActivity,
  ActivityType.LovedActivity,
)

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun Activity() {
  val pagerState = rememberPagerState(pageCount = 4)

  Column(Modifier.fillMaxSize()) {
    ScrollableTabRow(
      selectedTabIndex = pagerState.currentPage,
      backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.background else Color.Transparent,
      indicator = { tabPositions ->
        TabRowDefaults.Indicator(
          Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
        )
      }
    ) {
      val coroutineScope = rememberCoroutineScope()

      Activities.forEach {
        Tab(selected = pagerState.currentPage == Activities.indexOf(it),
          onClick = { coroutineScope.launch { pagerState.animateScrollToPage(Activities.indexOf(it)) } },
          text = { Text(text = it.title) })
      }
    }
    HorizontalPager(state = pagerState) { page ->
      when (Activities[page]) {
        ActivityType.TracksActivity -> {
          Tracks()
        }
        ActivityType.ArtistsActivity -> {
          Artists()
        }
        ActivityType.AlbumsActivity -> {
          Albums()
        }
        ActivityType.LovedActivity -> {
          LovedTracks()
        }
      }
    }
  }
}