package gt.com.pixela.jetfm.data.models.response

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.Artist
import gt.com.pixela.jetfm.data.models.common.Meta

data class TopArtists(
  @Json(name = "@attr") val meta: Meta,
  @Json(name = "artist") val artists: List<Artist> = listOf()
)