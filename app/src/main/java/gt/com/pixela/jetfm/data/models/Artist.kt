package gt.com.pixela.jetfm.data.models

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.common.Attributes
import gt.com.pixela.jetfm.data.models.common.Image

@Suppress("SpellCheckingInspection")
data class Artist(
  @Json(name = "@attr") val attributes: Attributes?,
  @Json(name = "playcount") val playCount: Int,
  val image: List<Image> = listOf(),
  val mbid: String,
  val name: String,
  val streamable: Int,
  val url: String,
)