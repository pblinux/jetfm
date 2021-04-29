package gt.com.pixela.jetfm.data.models

import gt.com.pixela.jetfm.data.models.response.TopAlbums
import gt.com.pixela.jetfm.data.models.response.TopArtists
import gt.com.pixela.jetfm.data.models.response.RecentTracks
import gt.com.pixela.jetfm.data.models.response.TopTracks

data class HomeInfo(
  val recentTracks: RecentTracks,
  val topAlbums: TopAlbums,
  val topArtists: TopArtists,
  val topTracks: TopTracks,
)