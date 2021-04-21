@file:Suppress("SpellCheckingInspection")

package gt.com.pixela.jetfm.data.models

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.common.Attributes
import gt.com.pixela.jetfm.data.models.common.Image
import gt.com.pixela.jetfm.data.models.common.InnerRecentTrackAlbum
import gt.com.pixela.jetfm.data.models.common.InnerRecentTrackArtist

data class RecentTrack(
    @Json(name = "@attr") val attributes: Attributes?,
    @Json(name = "mbid") val uid: String,
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

