package gt.com.pixela.jetfm.data.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Suppress("SpellCheckingInspection")
data class RecentTracksResult(@Json(name = "recenttracks") val recentTracks: RecentTracks) {
    class Deserializer : ResponseDeserializable<RecentTracksResult> {
        override fun deserialize(content: String): RecentTracksResult? =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(RecentTracksResult::class.java).fromJson(content)
    }
}

data class RecentTracks(
    @Json(name = "@attr") val meta: Meta,
    @Json(name = "track") val tracks: List<Track> = listOf()
) {
    class Deserializer : ResponseDeserializable<RecentTracks> {
        override fun deserialize(content: String): RecentTracks? =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(RecentTracks::class.java).fromJson(content)
    }
}