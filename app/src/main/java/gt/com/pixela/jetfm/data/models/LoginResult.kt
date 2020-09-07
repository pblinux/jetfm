package gt.com.pixela.jetfm.data.models

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class LoginResult(val session: Session) {
    class Deserializer : ResponseDeserializable<LoginResult> {
        override fun deserialize(content: String): LoginResult? =
            Gson().fromJson(content, LoginResult::class.java)
    }
}

data class Session(val subscriber: Int, val name: String, val key: String) {
    class Deserializer : ResponseDeserializable<Session> {
        override fun deserialize(content: String): Session? =
            Gson().fromJson(content, Session::class.java)
    }
}