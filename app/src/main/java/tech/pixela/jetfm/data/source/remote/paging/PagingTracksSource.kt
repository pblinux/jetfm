package tech.pixela.jetfm.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.pixela.jetfm.data.model.lastfm.base.Track
import tech.pixela.jetfm.data.model.lastfm.common.Period
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmSource

class PagingTracksSource(
    private val lastfmSource: LastfmSource,
    private val user: String,
    private val period: Period
) : PagingSource<Int, Track>() {
    override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
        return try {
            val next = params.key ?: 1
            val result = lastfmSource.getTopPeriodTracks(user, page = next, period = period.value)
            when (result.isSuccess) {
                true -> {
                    LoadResult.Page(
                        data = result.getOrThrow().topTracks.tracks,
                        prevKey = null,
                        nextKey = if (
                            next <= result.getOrThrow().topTracks.meta.totalPages
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
            LoadResult.Error(e)
        }
    }
}
