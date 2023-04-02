package tech.pixela.jetfm.data.model.lastfm.groups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.RecentTrack
import tech.pixela.jetfm.data.model.lastfm.common.Meta

@Serializable
data class RecentTracks(
    @SerialName("@attr") val meta: Meta,
    @SerialName("track") val recentTracks: List<RecentTrack> = listOf()
)
