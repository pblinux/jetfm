package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.groups.TopTracks

@Suppress("SpellCheckingInspection")
@Serializable
data class TopTracksResult(@SerialName("toptracks") val topTracks: TopTracks)
