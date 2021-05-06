package gt.com.pixela.jetfm.data.source

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.data.models.User
import gt.com.pixela.jetfm.data.models.response.*
import gt.com.pixela.jetfm.data.models.results.*
import kotlinx.coroutines.supervisorScope
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

enum class Period(val value: String) {
  Overall("overall"),
  Weekly("7day"),
  Monthly("1month"),
  Trimester("3month"),
  Biannual("6month"),
  Yearly("12month"),
}

class LastfmApiClient(val context: Context) {
  private lateinit var digest: MessageDigest

  // API Key
  private val key = context.getString(R.string.lastfm_key)
  private val secret = context.getString(R.string.lastfm_secret)

  //  Base URLs
  private val api = "https://ws.audioscrobbler.com/2.0"

  // URIs
  private val favTracks = "/?method=user.getlovedtracks&format=json"
  private val friends = "/?method=user.getfriends&format=json"
  private val infoUser = "/?method=user.getInfo&format=json"
  private val login = "/?method=auth.getMobileSession&format=json"
  private val recentTracks = "/?method=user.getRecentTracks&format=json"
  private val topAlbums = "/?method=user.gettopalbums&format=json"
  private val topArtists = "/?method=user.gettopartists&format=json"
  private val topTracks = "/?method=user.gettoptracks&format=json"

  init {
    try {
      digest = MessageDigest.getInstance("MD5")
    } catch (e: NoSuchAlgorithmException) {
    }
  }

  /*
  * Login
  * Authenticate user into Last.fm
  * */
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
        .fold(success = { it }, failure = { Log.e("Jetfm", it.message.toString()); null })
    }
  }

  /*
  * Get user info
  * Retrieves user information
  * */
  suspend fun getInfo(user: String): User {
    return supervisorScope {
      Fuel.get(
        api + infoUser,
        parameters = listOf(
          Pair(first = "user", second = user),
          Pair(first = "api_key", second = key),
        )
      ).awaitObjectResult(UserResult.Deserializer())
        .fold(success = { it.user }, failure = { throw Error(it.response.responseMessage) })
    }
  }

  /*
* Get user friends
* Retrieves user friends information
* */
  suspend fun getFriends(user: String, page: Int = 1): Friends {
    return supervisorScope {
      Fuel.get(
        api + friends,
        parameters = listOf(
          Pair(first = "user", second = user),
          Pair(first = "api_key", second = key),
          Pair("page", page)
        )
      ).awaitObjectResult(FriendsResult.Deserializer())
        .fold(success = { it.friends }, failure = {
          Log.d("JetFM: Error friends", it.response.statusCode.toString())
          Log.d("JetFM: Error friends", it.response.responseMessage)
          Log.d("JetFM: Error friends", it.message ?: "Error")
          throw Error(it.response.responseMessage)
        })
    }
  }

  /*
  * Recently track
  * Get most recent track for user
  * */
  suspend fun getRecentTracks(user: String, limit: Int = 20, page: Int = 1): RecentTracks {
    return supervisorScope {
      Fuel.get(
        api + recentTracks, parameters = listOf(
          Pair("user", user),
          Pair("api_key", key),
          Pair("limit", limit),
          Pair("page", page)
        )
      )
        .awaitObjectResult(RecentTracksResult.Deserializer())
        .fold(success = { it.recentTracks }, failure = {
          Log.d("JetFM: Error recent", it.response.statusCode.toString())
          throw Error(it.response.responseMessage)
        })
    }
  }

  /*
  * Top Period artists
  * Get top artists for user based on a period of time
  * */
  suspend fun getTopPeriodArtists(
    user: String,
    limit: Int = 10,
    period: Period = Period.Weekly,
    page: Int = 1
  ): TopArtists {
    return supervisorScope {
      Fuel.get(
        api + topArtists, parameters = listOf(
          Pair("user", user),
          Pair("api_key", key),
          Pair("period", period.value),
          Pair("limit", limit),
          Pair("page", page)
        )
      )
        .awaitObjectResult(TopArtistsResult.Deserializer())
        .fold(
          success = { it.topArtists },
          failure = {
            Log.d("JetFM: Error artists", it.response.statusCode.toString())
            throw Error(it.response.responseMessage)
          })
    }
  }

  /*
 * Top period albums
 * Get top albums for user based on a period of time
 * */
  suspend fun getTopPeriodAlbums(
    user: String,
    limit: Int = 10,
    period: Period = Period.Weekly,
    page: Int = 1
  ): TopAlbums {
    return supervisorScope {
      Fuel.get(
        api + topAlbums, parameters = listOf(
          Pair("user", user),
          Pair("api_key", key),
          Pair("period", period.value),
          Pair("limit", limit),
          Pair("page", page)
        )
      )
        .awaitObjectResult(TopAlbumsResult.Deserializer())
        .fold(
          success = { it.topAlbums },
          failure = {
            Log.d("JetFM: Error albums", it.response.statusCode.toString())
            throw Error(it.response.responseMessage)
          })
    }
  }

  /*
* Top period tracks
* Get top tracks for user based on a period of time
* */
  suspend fun getTopPeriodTracks(
    user: String,
    limit: Int = 10,
    period: Period = Period.Weekly,
    page: Int = 1
  ): TopTracks {
    return supervisorScope {
      Fuel.get(
        api + topTracks, parameters = listOf(
          Pair("user", user),
          Pair("api_key", key),
          Pair("period", period.value),
          Pair("limit", limit),
          Pair("page", page)
        )
      )
        .awaitObjectResult(TopTracksResult.Deserializer())
        .fold(
          success = { it.topTracks },
          failure = {
            Log.d("JetFM: Error tracks", it.response.statusCode.toString())
            throw Error(it.response.responseMessage)
          })
    }
  }

  /*
* Loved tracks
* Get loved albums for user
* */
  suspend fun getLovedTracks(
    user: String,
    limit: Int = 10,
    page: Int = 1
  ): FavTracks {
    return supervisorScope {
      Fuel.get(
        api + favTracks, parameters = listOf(
          Pair("user", user),
          Pair("api_key", key),
          Pair("limit", limit),
          Pair("page", page)
        )
      )
        .awaitObjectResult(FavTracksResult.Deserializer())
        .fold(
          success = { it.favTracks },
          failure = {
            Log.d("JetFM: Error tracks", it.response.statusCode.toString())
            throw Error(it.response.responseMessage)
          })
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




