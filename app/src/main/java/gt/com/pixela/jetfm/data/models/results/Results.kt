package gt.com.pixela.jetfm.data.models.results

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import gt.com.pixela.jetfm.data.models.Session
import gt.com.pixela.jetfm.data.models.User
import gt.com.pixela.jetfm.data.models.response.*

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

@Suppress("SpellCheckingInspection")
data class TopArtistsResult(@Json(name = "topartists") val topArtists: TopArtists) {
  class Deserializer : ResponseDeserializable<TopArtistsResult> {
    override fun deserialize(content: String): TopArtistsResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(TopArtistsResult::class.java).fromJson(content)
  }
}

@Suppress("SpellCheckingInspection")
data class TopAlbumsResult(@Json(name = "topalbums") val topAlbums: TopAlbums) {
  class Deserializer : ResponseDeserializable<TopAlbumsResult> {
    override fun deserialize(content: String): TopAlbumsResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(TopAlbumsResult::class.java).fromJson(content)
  }
}

@Suppress("SpellCheckingInspection")
data class TopTracksResult(@Json(name = "toptracks") val topTracks: TopTracks) {
  class Deserializer : ResponseDeserializable<TopTracksResult> {
    override fun deserialize(content: String): TopTracksResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(TopTracksResult::class.java).fromJson(content)
  }
}

@Suppress("SpellCheckingInspection")
data class FavTracksResult(@Json(name = "lovedtracks") val favTracks: FavTracks) {
  class Deserializer : ResponseDeserializable<FavTracksResult> {
    override fun deserialize(content: String): FavTracksResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(FavTracksResult::class.java).fromJson(content)
  }
}

data class UserResult(val user: User) {
  class Deserializer : ResponseDeserializable<UserResult> {
    override fun deserialize(content: String): UserResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(UserResult::class.java).fromJson(content)
  }
}

data class FriendsResult(@Json(name = "friends") val friends: Friends) {
  class Deserializer : ResponseDeserializable<FriendsResult> {
    override fun deserialize(content: String): FriendsResult? =
      Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        .adapter(FriendsResult::class.java).fromJson(content)
  }
}
