package tech.pixela.jetfm.data.model.lastfm.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InnerRecentTrackArtist(
  @SerialName("#text") val name: String,
  @SerialName("mbid") val uid: String
)

@Serializable
data class InnerRecentTrackAlbum(
  @SerialName("#text") val name: String,
  @SerialName("mbid") val uid: String
)

@Serializable
data class InnerTrackArtist(
  @SerialName("mbid") val uid: String,
  val name: String,
  val url: String
)

@Serializable
data class InnerAlbumArtist(val name: String, @SerialName("mbid") val uid: String, val url: String)