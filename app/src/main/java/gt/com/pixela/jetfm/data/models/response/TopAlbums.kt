package gt.com.pixela.jetfm.data.models.response

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.Album
import gt.com.pixela.jetfm.data.models.common.Meta

data class TopAlbums(
  @Json(name = "@attr") val meta: Meta,
  @Json(name = "album") val albums: List<Album> = listOf()
)