package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.groups.TopArtists

@Suppress("SpellCheckingInspection")
@Serializable
data class TopArtistsResult(@SerialName("topartists") val topArtists: TopArtists)