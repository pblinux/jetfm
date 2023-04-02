package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.common.Attributes
import tech.pixela.jetfm.data.model.lastfm.common.Date
import tech.pixela.jetfm.data.model.lastfm.common.Image
import tech.pixela.jetfm.data.model.lastfm.common.InnerTrackArtist

@Suppress("SpellCheckingInspection")
@Serializable
data class Track(
    @SerialName("@attr") val attributes: Attributes? = null,
    @SerialName("mbid") val uid: String,
    @SerialName("playcount") val playCount: Int? = null,
    val artist: InnerTrackArtist,
    val date: Date? = null,
    val duration: Int? = null,
    val image: List<Image> = listOf(),
    val name: String,
    val url: String
)
