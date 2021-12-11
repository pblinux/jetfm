package tech.pixela.jetfm.data.model.lastfm.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
  @SerialName("nowplaying") private val nowPlaying: String? = null,
  @SerialName("rank") private val rank: Int? = null
) {
  val isPlaying: Boolean
    get() = nowPlaying?.toBoolean() ?: false
}