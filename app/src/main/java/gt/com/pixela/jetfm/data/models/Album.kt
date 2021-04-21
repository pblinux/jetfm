package gt.com.pixela.jetfm.data.models

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.common.Attributes
import gt.com.pixela.jetfm.data.models.common.Image
import gt.com.pixela.jetfm.data.models.common.InnerAlbumArtist

@Suppress("SpellCheckingInspection")
data class Album(
  @Json(name = "@attr") val attributes: Attributes?,
  @Json(name = "playcount") val playCount: Int,
  val artist: InnerAlbumArtist,
  val image: List<Image> = listOf(),
  val mbid: String,
  val name: String,
  val url: String,
)

