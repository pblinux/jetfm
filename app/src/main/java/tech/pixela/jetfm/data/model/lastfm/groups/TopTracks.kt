package tech.pixela.jetfm.data.model.lastfm.groups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.Track
import tech.pixela.jetfm.data.model.lastfm.common.Meta

@Serializable
data class TopTracks(
    @SerialName("@attr") val meta: Meta,
    @SerialName("track") val tracks: List<Track> = listOf()
)
