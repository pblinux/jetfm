package gt.com.pixela.jetfm.data.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

data class RecentTracks(
  @Json(name = "@attr") val meta: Meta,
  @Json(name = "track") val tracks: List<Track> = listOf()
)