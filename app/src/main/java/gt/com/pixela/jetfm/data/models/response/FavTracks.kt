package gt.com.pixela.jetfm.data.models.response

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.Track
import gt.com.pixela.jetfm.data.models.common.Meta

data class FavTracks(
  @Json(name = "@attr") val meta: Meta,
  @Json(name = "track") val tracks: List<Track> = listOf()
)