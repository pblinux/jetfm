package tech.pixela.jetfm.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import tech.pixela.jetfm.data.model.lastfm.holders.HomeHolder
import tech.pixela.jetfm.data.model.lastfm.result.LoginResult
import tech.pixela.jetfm.data.model.lastfm.result.UserResult
import tech.pixela.jetfm.data.model.utils.JetResult
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmSource
import javax.inject.Inject

class LastfmRepository @Inject constructor(
    val lastfmSource: LastfmSource
) {

    /*
    * Login
    * Authenticate user into Last.fm
    * */
    suspend fun login(username: String, password: String): Flow<JetResult<LoginResult>> {
        return getSingle(lastfmSource.login(username = username, password = password))
    }

    /*
    * Home info
    * Get all the info for home page
    *  */
    suspend fun getHome(
        user: String,
        refresh: Boolean = false,
        previousState: HomeHolder? = null,
    ): Flow<JetResult<HomeHolder>> {
        return getSingle(
            refresh = refresh,
            previousState = previousState
        ) {
            try {
                val recentTracks = lastfmSource.getRecentTracks(user)
                val topWeeklyArtists = lastfmSource.getTopPeriodArtists(user)
                val topWeeklyAlbums = lastfmSource.getTopPeriodAlbums(user)
                val topWeeklyTracks = lastfmSource.getTopPeriodTracks(user)
                Result.success(
                    HomeHolder(
                        recentTracks = recentTracks.getOrThrow().recentTracks,
                        topAlbums = topWeeklyAlbums.getOrThrow().topAlbums,
                        topArtists = topWeeklyArtists.getOrThrow().topArtists,
                        topTracks = topWeeklyTracks.getOrThrow().topTracks
                    )
                )
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    /*
    * User profile info
    * Get the user related info
    *  */
    suspend fun getProfile(
        user: String,
        refresh: Boolean = false,
        previousState: UserResult? = null,
    ): Flow<JetResult<UserResult>> {
        return getSingle(
            refresh = refresh,
            previousState = previousState
        ) {
            lastfmSource.getUserInfo(user)
        }
    }

    /*
    * Common flows
    * */
    // Get data and handle all the emits for flow for a single result
    private suspend fun <T> getSingle(result: Result<T>): Flow<JetResult<T>> {
        return flow {
            emit(JetResult.Loading)
            when (result.isSuccess) {
                true -> {
                    with(result.getOrNull()) {
                        this?.let { emit(JetResult.Loaded(this)) }
                            ?: emit(JetResult.Empty)
                    }
                }
                false -> {
                    result.exceptionOrNull()?.let {
                        emit(JetResult.Error(it.message ?: ""))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun <T> getSingle(
        refresh: Boolean = false,
        previousState: T? = null,
        operation: suspend () -> (Result<T>),
    ): Flow<JetResult<T>> {
        return flow {
            if (refresh && previousState != null)
                emit(JetResult.Refresh(previousState))
            else
                emit(JetResult.Loading)
            val result = operation()
            when (result.isSuccess) {
                true -> {
                    with(result.getOrNull()) {
                        this?.let { emit(JetResult.Loaded(this)) }
                            ?: emit(JetResult.Empty)
                    }
                }
                false -> {
                    result.exceptionOrNull()?.let {
                        emit(JetResult.Error(it.message ?: ""))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}