package gt.com.pixela.jetfm.data.models

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.common.Attributes
import gt.com.pixela.jetfm.data.models.common.Date
import gt.com.pixela.jetfm.data.models.common.Image
import gt.com.pixela.jetfm.data.models.common.InnerTrackArtist

data class Track(
  @Json(name = "@attr") val attributes: Attributes?,
  @Json(name = "mbid") val uid: String,
  @Json(name = "playcount") val playCount: Int?,
  val artist: InnerTrackArtist,
  val date: Date?,
  val duration: Int?,
  val image: List<Image> = listOf(),
  val name: String,
  val url: String,
)