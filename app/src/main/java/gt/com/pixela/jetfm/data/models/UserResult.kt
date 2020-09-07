package gt.com.pixela.jetfm.data.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

data class UserResult(val user: User) {
    class Deserializer : ResponseDeserializable<UserResult> {
        override fun deserialize(content: String): UserResult? =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(UserResult::class.java).fromJson(content)
    }
}

@Suppress("SpellCheckingInspection")
data class User(
    val age: String,
    val bootstrap: String,
    val country: String,
    val gender: String,
    val image: List<Image>,
    val name: String,
    val playcount: String,
    val playlists: String,
    val realname: String,
    val registered: Registered,
    val subscriber: Int,
    val url: String,
) {
    class Deserializer : ResponseDeserializable<User> {
        override fun deserialize(content: String): User? =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(User::class.java).fromJson(content)
    }
}