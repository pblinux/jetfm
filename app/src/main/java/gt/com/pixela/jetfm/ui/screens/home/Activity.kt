package gt.com.pixela.jetfm.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
fun Activity() {
  var selectedActivity by remember { mutableStateOf<ActivityType>(ActivityType.TracksActivity) }

  Column(Modifier.fillMaxSize()) {
    ScrollableTabRow(
      selectedTabIndex = Activities.indexOf(selectedActivity),
      backgroundColor = if (isSystemInDarkTheme()) MaterialTheme.colors.background else Color.Transparent,
    ) {
      Activities.forEach {
        Tab(selected = it == selectedActivity, onClick = { selectedActivity = it },
          text = { Text(text = it.title) })
      }
    }
    when (selectedActivity) {
      ActivityType.TracksActivity -> {
        Text(text = "Hola 1")
      }
      ActivityType.ArtistsActivity -> {
        Text(text = "Hola 2")
      }
      ActivityType.AlbumsActivity -> {
        Text(text = "Hola 3")
      }
      ActivityType.LovedActivity -> {
        Text(text = "Hola 4")
      }
    }
  }
}