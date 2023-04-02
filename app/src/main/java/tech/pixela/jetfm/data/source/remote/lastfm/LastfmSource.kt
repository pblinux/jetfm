package tech.pixela.jetfm.data.source.remote.lastfm

import android.util.Log
import retrofit2.Response
import tech.pixela.jetfm.data.model.lastfm.common.Period
import tech.pixela.jetfm.data.model.lastfm.result.*
import tech.pixela.jetfm.utils.general.LastfmSignature
import javax.inject.Inject

class LastfmSource @Inject constructor(
    private val service: LastfmService,
    private val key: String,
    private val secret: String
) {
    /*
    * Login
    * Authenticate user into Last.fm
    * */
    suspend fun login(username: String, password: String): Result<LoginResult> {
        return response {
            service.login(
                username = username,
                password = password,
                key = key,
                signature = LastfmSignature.signature(
                    key,
                    password,
                    secret,
                    username
                )
            )
        }
    }

    /*
    * Get user info
    * Retrieves user information
    * */
    suspend fun getUserInfo(
        user: String
    ): Result<UserResult> {
        return response { service.getUserInfo(user = user, key = key) }
    }

    /*
    * Get user friends
    * Retrieves user friends information
    * */
    suspend fun getFriends(
        user: String,
        page: Int = 1
    ): Result<FriendsResult> {
        return response { service.getFriends(user = user, key = key, page = page) }
    }

    /*
    * Recently track
    * Get most recent track for user
    * */
    suspend fun getRecentTracks(
        user: String,
        limit: Int = 20,
        page: Int = 1
    ): Result<RecentTracksResult> {
        return response { service.getRecentTracks(user, key, limit, page) }
    }

    /*
    * Top Period artists
    * Get top artists for user based on a period of time
    * */
    suspend fun getTopPeriodArtists(
        user: String,
        period: String = Period.Weekly.value,
        limit: Int = 10,
        page: Int = 1
    ): Result<TopArtistsResult> {
        return response { service.getTopPeriodArtists(user, key, period, limit, page) }
    }

    /*
    * Top period albums
    * Get top albums for user based on a period of time
    * */
    suspend fun getTopPeriodAlbums(
        user: String,
        period: String = Period.Weekly.value,
        limit: Int = 10,
        page: Int = 1
    ): Result<TopAlbumsResult> {
        return response { service.getTopPeriodAlbums(user, key, period, limit, page) }
    }

    /*
    * Top period tracks
    * Get top tracks for user based on a period of time
    * */
    suspend fun getTopPeriodTracks(
        user: String,
        period: String = Period.Weekly.value,
        limit: Int = 10,
        page: Int = 1
    ): Result<TopTracksResult> {
        return response { service.getTopPeriodTracks(user, key, period, limit, page) }
    }

    /*
    * Loved tracks
    * Get loved albums for user
    * */
    suspend fun getLovedTracks(
        user: String,
        limit: Int = 10,
        page: Int = 1
    ): Result<LovedTracksResult> {
        return response { service.getLovedTracks(user, key, limit, page) }
    }

    /*
    * General response
    * */
    private suspend fun <T> response(
        request: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val result = request.invoke()
            result.body()?.let { Result.success(it) }
                ?: Result.failure(Exception())
        } catch (e: Throwable) {
            Log.d("TEST", e.message ?: "empty")
            Result.failure(e)
        }
    }
}
