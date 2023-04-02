package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.groups.RecentTracks

@Suppress("SpellCheckingInspection")
@Serializable
data class RecentTracksResult(@SerialName("recenttracks") val recentTracks: RecentTracks)
