package gt.com.pixela.jetfm.data.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

data class LoginResult(val session: Session) {
    class Deserializer : ResponseDeserializable<LoginResult> {
        override fun deserialize(content: String): LoginResult? =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(LoginResult::class.java).fromJson(content)
    }
}

data class Session(val subscriber: Int, val name: String, val key: String) {
    class Deserializer : ResponseDeserializable<Session> {
        override fun deserialize(content: String): Session? =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                .adapter(Session::class.java).fromJson(content)
    }
}