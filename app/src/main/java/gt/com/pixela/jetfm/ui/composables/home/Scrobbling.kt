@file:Suppress("SpellCheckingInspection")

package gt.com.pixela.jetfm.ui.composables.home

import android.widget.Space
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.android.material.color.MaterialColors
import dev.chrisbanes.accompanist.coil.CoilImageWithCrossfade
import gt.com.pixela.jetfm.data.models.Track
import gt.com.pixela.jetfm.ui.theme.shapes

@Composable
fun Scrobbling(track: Track, isPlaying: Boolean = false) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        shape = if (isPlaying) shapes.large else shapes.medium,
        content = {
            Stack(
                children = {
                    CoilImageWithCrossfade(
                        data = track.image.first { it.size == "extralarge" }.url,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth().height(150.dp)
                            .background(color = Color.Red.copy(alpha = 0.4f))
                    )
                    Surface(
                        modifier = Modifier.fillMaxWidth().height(150.dp),
                        color = if (isSystemInDarkTheme())
                            Color.Black.copy(0.70f)
                        else Color.White.copy(0.7f)
                    ) {}
                    Row(
                        modifier = Modifier.fillMaxSize().height(150.dp)
                            .padding(horizontal = 16.dp),
                        verticalGravity = Alignment.CenterVertically,
                        children = {
                            CoilImageWithCrossfade(
                                data = track.image.first { it.size == "large" }.url,
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier.size(64.dp).clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            Column(
                                children = {
                                    Text(
                                        text = track.artist.name,
                                        style = MaterialTheme.typography.caption
                                    )
                                    Text(
                                        text = track.name,
                                        style = MaterialTheme.typography.subtitle2
                                    )
                                    Text(
                                        text = track.album.name,
                                        style = MaterialTheme.typography.overline
                                    )
                                }
                            )

                        })
                }
            )
        }
    )
}