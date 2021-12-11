package tech.pixela.jetfm.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.pixela.jetfm.data.model.lastfm.base.Friend
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmSource

class PagingFriendsSource(
    private val lastfmSource: LastfmSource,
    private val user: String,
) : PagingSource<Int, Friend>() {
    override fun getRefreshKey(state: PagingState<Int, Friend>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Friend> {
        return try {
            val next = params.key ?: 1
            val result = lastfmSource.getFriends(user, page = next)
            when (result.isSuccess) {
                true -> {
                    LoadResult.Page(
                        data = result.getOrThrow().friends.friends,
                        prevKey = null,
                        nextKey = if (
                            next <= result.getOrThrow().friends.meta.totalPages) next.plus(1)
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