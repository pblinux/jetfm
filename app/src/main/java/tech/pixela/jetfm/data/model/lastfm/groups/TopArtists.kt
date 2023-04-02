package tech.pixela.jetfm.data.model.lastfm.groups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.Artist
import tech.pixela.jetfm.data.model.lastfm.common.Meta

@Serializable
data class TopArtists(
    @SerialName("@attr") val meta: Meta,
    @SerialName("artist") val artists: List<Artist> = listOf()
)
