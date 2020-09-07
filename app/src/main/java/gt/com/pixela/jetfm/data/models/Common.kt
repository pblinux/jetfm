package gt.com.pixela.jetfm.data.models

import com.squareup.moshi.Json

data class Track(
    val streamable: String,
    val artist: Artist,
    @Json(name = "@attr") val attributes: Attributes?
) {}

data class Artist(@Json(name = "#text") val name: String) {}

@Suppress("SpellCheckingInspection")
data class Attributes(@Json(name = "nowplaying") val nowPlaying: Boolean = false) {}

data class Image(val size: String, @Json(name = "#text") val url: String) {}

data class Registered(@Json(name = "#text") val timestamp: Int) {}

data class Meta(
    val page: Int,
    val perPage: Int,
    val user: String,
    val total: Int,
    val totalPages: Int
) {}