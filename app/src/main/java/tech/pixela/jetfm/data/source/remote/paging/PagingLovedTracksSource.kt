package tech.pixela.jetfm.data.source.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.pixela.jetfm.data.model.lastfm.base.Track
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmSource

class PagingLovedTracksSource(
    private val lastfmSource: LastfmSource,
    private val user: String
) : PagingSource<Int, Track>() {
    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        return try {
            val next = params.key ?: 1
            val result = lastfmSource.getLovedTracks(user, page = next)
            Log.d("TEST", "AQUI")
            Log.d("TEST", result.isSuccess.toString())
            when (result.isSuccess) {
                true -> {
                    LoadResult.Page(
                        data = result.getOrThrow().lovedTracks.lovedTracks,
                        prevKey = null,
                        nextKey = if (
                            next <= result.getOrThrow().lovedTracks.meta.totalPages
                        ) {
                            next.plus(1)
                        } else {
                            null
                        }
                    )
                }
                false -> {
                    LoadResult.Error(result.exceptionOrNull() ?: Exception())
                }
            }
        } catch (e: Error) {
            Log.d("TEST", "AQUI 2")
            Log.d("TEST", e.message ?: "")
            LoadResult.Error(e)
        }
    }
}
