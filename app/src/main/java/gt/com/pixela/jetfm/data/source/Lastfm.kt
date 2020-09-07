package gt.com.pixela.jetfm.data.source

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import gt.com.pixela.jetfm.data.models.LoginResult
import kotlinx.coroutines.supervisorScope
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LastfmApiClient {
    private lateinit var digest: MessageDigest

    private val api = "https://ws.audioscrobbler.com/2.0"
    private val login = "/?method=auth.getMobileSession&format=json"
    private val key = "5cb87d3af5c6c1ea5f6a9b0692845578"
    private val secret = "0f3e535599a5f511e46347821c5a9e77"

    init {
        try {
            digest = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
        }
    }

    suspend fun login(username: String, password: String): LoginResult? {
        return supervisorScope {
            Fuel.post(
                api + login, parameters = listOf(
                    Pair(first = "api_key", second = key),
                    Pair(first = "api_sig", second = signature(key, password, secret, username)),
                    Pair(first = "password", second = password),
                    Pair(first = "username", second = username)
                )
            ).awaitObjectResult(LoginResult.Deserializer())
                .fold(success = { it }, failure = { null })
        }
    }


    private fun signature(key: String, password: String, secret: String, username: String) =
        md5("api_key${key}methodauth.getMobileSessionpassword${password}username${username}$secret")

    private fun md5(text: String): String {
        return try {
            val bytes: ByteArray = digest.digest(text.toByteArray())
            val builder = StringBuilder(32)
            bytes.forEach {
                val hex = Integer.toHexString(it.toInt() and 0xFF)
                if (hex.length == 1) builder.append('0')
                builder.append(hex)
            }
            builder.toString()
        } catch (e: UnsupportedEncodingException) {
            ""
        }
    }
}




