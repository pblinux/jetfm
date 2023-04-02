package tech.pixela.jetfm.data.source.remote.lastfm

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import tech.pixela.jetfm.data.model.lastfm.common.Period
import tech.pixela.jetfm.data.model.lastfm.result.FriendsResult
import tech.pixela.jetfm.data.model.lastfm.result.LoginResult
import tech.pixela.jetfm.data.model.lastfm.result.LovedTracksResult
import tech.pixela.jetfm.data.model.lastfm.result.RecentTracksResult
import tech.pixela.jetfm.data.model.lastfm.result.TopAlbumsResult
import tech.pixela.jetfm.data.model.lastfm.result.TopArtistsResult
import tech.pixela.jetfm.data.model.lastfm.result.TopTracksResult
import tech.pixela.jetfm.data.model.lastfm.result.UserResult

interface LastfmService {
    /*
    * Login
    * Authenticate user into Last.fm
    * */
    @POST("?method=auth.getMobileSession&format=json")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("api_key") key: String,
        @Query("api_sig") signature: String
    ): Response<LoginResult>

    /*
    * Get user info
    * Retrieves user information
    * */
    @GET("?method=user.getInfo&format=json")
    suspend fun getUserInfo(
        @Query("user") user: String,
        @Query("api_key") key: String
    ): Response<UserResult>

    /*
    * Get user friends
    * Retrieves user friends information
    * */
    @GET("?method=user.getfriends&format=json")
    suspend fun getFriends(
        @Query("user") user: String,
        @Query("api_key") key: String,
        @Query("page") page: Int = 1
    ): Response<FriendsResult>

    /*
    * Recently track
    * Get most recent track for user
    * */
    @GET("?method=user.getRecentTracks&format=json")
    suspend fun getRecentTracks(
        @Query("user") user: String,
        @Query("api_key") key: String,
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 1
    ): Response<RecentTracksResult>

    /*
    * Top Period artists
    * Get top artists for user based on a period of time
    * */
    @GET("?method=user.gettopartists&format=json")
    suspend fun getTopPeriodArtists(
        @Query("user") user: String,
        @Query("api_key") key: String,
        @Query("period") period: String = Period.Weekly.value,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1
    ): Response<TopArtistsResult>

    /*
    * Top period albums
    * Get top albums for user based on a period of time
    * */
    @GET("?method=user.gettopalbums&format=json")
    suspend fun getTopPeriodAlbums(
        @Query("user") user: String,
        @Query("api_key") key: String,
        @Query("period") period: String = Period.Weekly.value,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1
    ): Response<TopAlbumsResult>

    /*
    * Top period tracks
    * Get top tracks for user based on a period of time
    * */
    @GET("?method=user.gettoptracks&format=json")
    suspend fun getTopPeriodTracks(
        @Query("user") user: String,
        @Query("api_key") key: String,
        @Query("period") period: String = Period.Weekly.value,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1
    ): Response<TopTracksResult>

    /*
    * Loved tracks
    * Get loved albums for user
    * */
    @GET("?method=user.getlovedtracks&format=json")
    suspend fun getLovedTracks(
        @Query("user") user: String,
        @Query("api_key") key: String,
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1
    ): Response<LovedTracksResult>
}
