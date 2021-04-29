package gt.com.pixela.jetfm.data.models.response

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.RecentTrack
import gt.com.pixela.jetfm.data.models.common.Meta

data class RecentTracks(
  @Json(name = "@attr") val meta: Meta,
  @Json(name = "track") val recentTracks: List<RecentTrack> = listOf()
)