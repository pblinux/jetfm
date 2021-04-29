package gt.com.pixela.jetfm.ui.composables.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.data.models.User
import gt.com.pixela.jetfm.ui.theme.primaryRed


@Composable
fun InfoHeader(user: User) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Image(
      modifier = Modifier
        .size(80.dp),
      painter = rememberCoilPainter(
        previewPlaceholder = R.drawable.ic_launcher_background,
        request = user.image.single { it.size == "extralarge" }.url,
        requestBuilder = {
          transformations(CircleCropTransformation())
        }
      ),
      contentDescription = user.name
    )
    Spacer(Modifier.width(16.dp))
    Column(
      Modifier
        .fillMaxWidth()
    ) {
      Text(
        user.realname,
        style = MaterialTheme.typography.h4,
      )
      Spacer(Modifier.height(4.dp))
      Text(
        user.name,
        style = MaterialTheme.typography.h6,
      )
      Spacer(Modifier.height(8.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        InfoItem(user.playcount, "Scrobbles")
        InfoItem(user.subscriber, "Subscribers")
        InfoItem(user.playlists, "Playlists")
      }
    }
  }
}

@Composable
fun InfoItem(number: Int, description: String) {
  Column() {
    Text(
      "$number",
      color = primaryRed,
      style = MaterialTheme.typography.subtitle1,
    )
    Spacer(Modifier.height(2.dp))
    Text(
      description,
      style = MaterialTheme.typography.subtitle2,
    )
  }
}