package gt.com.pixela.jetfm.data.models.results

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import gt.com.pixela.jetfm.data.models.RecentTracks
import gt.com.pixela.jetfm.data.models.Session
import gt.com.pixela.jetfm.data.models.User

data class LoginResult(val session: Session) {
  class Deserializer : ResponseDeserializable<LoginResult> {
    override fun deserialize(content: String): LoginResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(LoginResult::class.java).fromJson(content)
  }
}

@Suppress("SpellCheckingInspection")
data class RecentTracksResult(@Json(name = "recenttracks") val recentTracks: RecentTracks) {
  class Deserializer : ResponseDeserializable<RecentTracksResult> {
    override fun deserialize(content: String): RecentTracksResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(RecentTracksResult::class.java).fromJson(content)
  }
}

data class UserResult(val user: User) {
  class Deserializer : ResponseDeserializable<UserResult> {
    override fun deserialize(content: String): UserResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(UserResult::class.java).fromJson(content)
  }
}
