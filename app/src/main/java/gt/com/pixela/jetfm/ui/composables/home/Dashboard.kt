package gt.com.pixela.jetfm.ui.composables.home

import android.util.Log
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import gt.com.pixela.jetfm.data.models.Track
import gt.com.pixela.jetfm.data.models.User
import gt.com.pixela.jetfm.data.vm.HomeViewModel
import gt.com.pixela.jetfm.data.vm.ResultState

@Composable
fun Dashboard(viewModel: HomeViewModel) {
    val infoState by viewModel.currentInfo.observeAsState(initial = ResultState.Uninitialized)
    val recentState by viewModel.currentRecentTracks.observeAsState(initial = ResultState.Uninitialized)

    ScrollableColumn(
        contentPadding = InnerPadding(bottom = 64.dp),
        children = {
            UserInfo(currentState = infoState)
            CurrentlyPlaying(currentState = recentState)
            Spacer(Modifier.padding(top = 8.dp))
            LatestScrobblings(currentState = recentState)
        }
    )
}

@Composable
fun UserInfo(currentState: ResultState) {
    when (currentState) {
        is ResultState.Loaded -> {
            val user = (currentState.data as User)
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 24.dp),
                verticalGravity = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                children = {
                    Column {
                        Text(text = "Welcome", style = MaterialTheme.typography.h4)
                        Spacer(Modifier.padding(top = 4.dp))
                        Text(
                            text = user.realname,
                            style = MaterialTheme.typography.h6
                        )
                    }
                    CoilImageWithCrossfade(
                        data = user.image.first { it.size == "large" }.url,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(80.dp).clip(CircleShape)
                    )
                }
            )
        }
        else -> {
        }
    }
}

@Composable
fun CurrentlyPlaying(currentState: ResultState) {
    when (currentState) {
        is ResultState.Loaded -> {
            val tracks = (currentState.data) as List<*>
            val current: Track? =
                tracks.firstOrNull { (it as Track).attributes?.isPlaying ?: false } as Track?
            current?.let {
                Column {
                    Text(
                        text = "Now playing",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Scrobbling(track = current, isPlaying = true)
                }
            }
        }
        else -> {
        }
    }
}

@Suppress("SpellCheckingInspection")
@Composable
fun LatestScrobblings(currentState: ResultState) {
    when (currentState) {
        is ResultState.Loaded -> {
            val tracks = (currentState.data) as List<*>
            ScrollableColumn() {
                Text(
                    text = "Most recents",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumnFor(items = tracks) { track ->
                    if ((track as Track).attributes == null)
                        Scrobbling(track = track)
                }
            }
        }
        else -> {

        }
    }
}