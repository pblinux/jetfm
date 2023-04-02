package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.common.Attributes
import tech.pixela.jetfm.data.model.lastfm.common.Image

@Suppress("SpellCheckingInspection")
@Serializable
data class Artist(
    @SerialName("@attr") val attributes: Attributes? = null,
    @SerialName("playcount") val playCount: Int,
    val image: List<Image> = listOf(),
    val mbid: String,
    val name: String,
    val streamable: Int,
    val url: String
)
