package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.groups.TopAlbums

@Suppress("SpellCheckingInspection")
@Serializable
data class TopAlbumsResult(@SerialName("topalbums") val topAlbums: TopAlbums)
