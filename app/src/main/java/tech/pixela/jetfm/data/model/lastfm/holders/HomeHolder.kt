package tech.pixela.jetfm.data.model.lastfm.holders

import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.groups.RecentTracks
import tech.pixela.jetfm.data.model.lastfm.groups.TopAlbums
import tech.pixela.jetfm.data.model.lastfm.groups.TopArtists
import tech.pixela.jetfm.data.model.lastfm.groups.TopTracks

@Serializable
data class HomeHolder(
    val recentTracks: RecentTracks,
    val topAlbums: TopAlbums,
    val topArtists: TopArtists,
    val topTracks: TopTracks
)
