package tech.pixela.jetfm.data.model.lastfm.groups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.Album
import tech.pixela.jetfm.data.model.lastfm.common.Meta

@Serializable
data class TopAlbums(
  @SerialName("@attr") val meta: Meta,
  @SerialName("album") val albums: List<Album> = listOf()
)