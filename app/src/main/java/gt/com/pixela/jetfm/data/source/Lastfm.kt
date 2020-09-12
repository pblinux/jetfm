package gt.com.pixela.jetfm.data.source

import android.util.Log
import androidx.preference.PreferenceManager
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import gt.com.pixela.jetfm.data.models.*
import kotlinx.coroutines.supervisorScope
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LastfmApiClient {
    private lateinit var digest: MessageDigest

    private val api = "https://ws.audioscrobbler.com/2.0"
    private val infoUser = "/?method=user.getInfo&format=json"
    private val key = "APIKEY"
    private val login = "/?method=auth.getMobileSession&format=json"
    private val recentTracks = "/?method=user.getRecentTracks&format=json"
    private val secret = "APISECRET"

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

    suspend fun getInfo(user: String): User? {
        return supervisorScope {
            Fuel.get(
                api + infoUser,
                parameters = listOf(
                    Pair(first = "user", second = user),
                    Pair(first = "api_key", second = key),
                )
            ).awaitObjectResult(UserResult.Deserializer())
                .fold(success = { it.user }, failure = { null })
        }
    }

    suspend fun getRecentTracks(user: String, page: Int = 1): RecentTracks? {
        return supervisorScope {
            Fuel.get(
                api + recentTracks,
                parameters = listOf(
                    Pair(first = "user", second = user),
                    Pair(first = "api_key", second = key),
                    Pair(first = "page", second = page.toString())
                )
            ).awaitObjectResult(RecentTracksResult.Deserializer())
                .fold(success = { it.recentTracks }, failure = { null })
        }
    }

    @Suppress("SameParameterValue", "SpellCheckingInspection")
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




