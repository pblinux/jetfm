package gt.com.pixela.jetfm.data.models.common

import com.squareup.moshi.Json

data class InnerRecentTrackArtist(
  @Json(name = "#text") val name: String,
  @Json(name = "mbid") val uid: String
)

data class InnerRecentTrackAlbum(
  @Json(name = "#text") val name: String,
  @Json(name = "mbid") val uid: String
)

data class InnerTrackArtist(
  @Json(name = "mbid") val uid: String,
  val name: String,
  val url: String
)

data class InnerAlbumArtist(val name: String, @Json(name = "mbid") val uid: String, val url: String)