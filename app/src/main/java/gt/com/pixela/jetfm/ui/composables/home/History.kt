package gt.com.pixela.jetfm.ui.composables.home

import android.util.Log
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.onActive
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gt.com.pixela.jetfm.data.vm.HomeViewModel
import gt.com.pixela.jetfm.data.vm.TracksState

@Composable
fun History(viewModel: HomeViewModel) {
    val currentTracksState =
        viewModel.currentHistoryTracks.observeAsState(initial = TracksState.Uninitialized)
    when (currentTracksState.value) {
        is TracksState.Loaded -> {
            val tracks = ((currentTracksState.value) as TracksState.Loaded).tracks
            ScrollableColumn {
                HistoryHeader()
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumnForIndexed(items = tracks) { index, item ->
                    if (tracks.lastIndex == index) {
                        onActive(callback = {
                            viewModel.getHistoryTracks()
                        })
                    }
                    Scrobbling(track = item)
                }
            }
        }
        else -> {
        }
    }
}

@Composable
fun HistoryHeader() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp)) {
        Text(text = "History", style = MaterialTheme.typography.h4)
        Spacer(Modifier.padding(top = 4.dp))
        Text(
            "All your scrobbling history",
            style = MaterialTheme.typography.h6
        )
    }
}