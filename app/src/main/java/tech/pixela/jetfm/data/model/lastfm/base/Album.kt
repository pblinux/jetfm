package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.common.Attributes
import tech.pixela.jetfm.data.model.lastfm.common.Image
import tech.pixela.jetfm.data.model.lastfm.common.InnerAlbumArtist

@Suppress("SpellCheckingInspection")
@Serializable
data class Album(
    @SerialName("@attr") val attributes: Attributes? = null,
    @SerialName("playcount") val playCount: Int,
    val artist: InnerAlbumArtist,
    val image: List<Image> = listOf(),
    val mbid: String,
    val name: String,
    val url: String
)
