package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.SerialName
import tech.pixela.jetfm.data.model.lastfm.common.Attributes
import tech.pixela.jetfm.data.model.lastfm.common.Image
import tech.pixela.jetfm.data.model.lastfm.common.InnerAlbumArtist
import kotlinx.serialization.Serializable

@Suppress("SpellCheckingInspection")
@Serializable
data class Album(
  @SerialName("@attr") val attributes: Attributes? = null,
  @SerialName("playcount") val playCount: Int,
  val artist: InnerAlbumArtist,
  val image: List<Image> = listOf(),
  val mbid: String,
  val name: String,
  val url: String,
)

