package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.groups.LovedTracks

@Suppress("SpellCheckingInspection")
@Serializable
data class LovedTracksResult(@SerialName("lovedtracks") val lovedTracks: LovedTracks)