@file:Suppress("SpellCheckingInspection")

package gt.com.pixela.jetfm.data.models

import com.squareup.moshi.Json

data class Track(
    @Json(name = "@attr") val attributes: Attributes?,
    @Json(name = "mbid") val uid: String,
    val album: Album,
    val artist: Artist,
    val image: List<Image> = listOf(),
    val name: String,
    val streamable: Int,
    val url: String
) {
    override fun toString(): String {
        return "$name - ${artist.name} - ${album.name} - ${attributes?.isPlaying}"
    }
}

data class Artist(@Json(name = "#text") val name: String, @Json(name = "mbid") val uid: String)

data class Attributes(@Json(name = "nowplaying") private val nowPlaying: String) {
    val isPlaying: Boolean
        get() = nowPlaying.toBoolean()
}

data class Album(@Json(name = "#text") val name: String, @Json(name = "mbid") val uid: String)

data class Image(val size: String, @Json(name = "#text") val url: String)

data class Registered(@Json(name = "#text") val timestamp: Int)

data class Meta(
    val page: Int,
    val perPage: Int,
    val user: String,
    val total: Int,
    val totalPages: Int
)