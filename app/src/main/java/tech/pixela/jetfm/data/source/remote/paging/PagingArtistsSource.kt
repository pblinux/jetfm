package tech.pixela.jetfm.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.pixela.jetfm.data.model.lastfm.base.Artist
import tech.pixela.jetfm.data.model.lastfm.common.Period
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmSource

class PagingArtistsSource(
    private val lastfmSource: LastfmSource,
    private val user: String,
    private val period: Period
) : PagingSource<Int, Artist>() {
    override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val next = params.key ?: 1
            val result = lastfmSource.getTopPeriodArtists(user, page = next, period = period.value)
            when (result.isSuccess) {
                true -> {
                    LoadResult.Page(
                        data = result.getOrThrow().topArtists.artists,
                        prevKey = null,
                        nextKey = if (
                            next <= result.getOrThrow().topArtists.meta.totalPages
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
