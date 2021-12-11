package tech.pixela.jetfm.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.pixela.jetfm.data.model.lastfm.base.Album
import tech.pixela.jetfm.data.model.lastfm.common.Period
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmSource

class PagingAlbumsSource(
    private val lastfmSource: LastfmSource,
    private val user: String,
    private val period: Period,
) : PagingSource<Int, Album>() {
    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        return try {
            val next = params.key ?: 1
            val result = lastfmSource.getTopPeriodAlbums(user, page = next, period = period.value)
            when (result.isSuccess) {
                true -> {
                    LoadResult.Page(
                        data = result.getOrThrow().topAlbums.albums,
                        prevKey = null,
                        nextKey = if (
                            next <= result.getOrThrow().topAlbums.meta.totalPages) next.plus(1)
                        else null
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