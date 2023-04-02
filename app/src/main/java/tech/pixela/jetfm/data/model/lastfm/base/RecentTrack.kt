package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.common.Attributes
import tech.pixela.jetfm.data.model.lastfm.common.Image
import tech.pixela.jetfm.data.model.lastfm.common.InnerRecentTrackAlbum
import tech.pixela.jetfm.data.model.lastfm.common.InnerRecentTrackArtist

@Suppress("SpellCheckingInspection")
@Serializable
data class RecentTrack(
    @SerialName("@attr") val attributes: Attributes? = null,
    @SerialName("mbid") val uid: String,
    val album: InnerRecentTrackAlbum,
    val artist: InnerRecentTrackArtist,
    val image: List<Image> = listOf(),
    val name: String,
    val streamable: Int,
    val url: String
) {
    override fun toString(): String {
        return "$name - ${artist.name} - ${album.name} - ${attributes?.isPlaying}"
    }
}
