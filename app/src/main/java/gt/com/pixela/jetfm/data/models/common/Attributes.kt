package gt.com.pixela.jetfm.data.models.common

import com.squareup.moshi.Json

data class Attributes(
  @Json(name = "nowplaying") private val nowPlaying: String?,
  @Json(name = "rank") private val rank: Int?
) {
  val isPlaying: Boolean
    get() = nowPlaying?.toBoolean() ?: false
}