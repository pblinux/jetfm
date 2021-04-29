package gt.com.pixela.jetfm.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import gt.com.pixela.jetfm.data.models.Album
import gt.com.pixela.jetfm.data.models.Artist
import gt.com.pixela.jetfm.data.models.Track
import gt.com.pixela.jetfm.data.models.User

class PaginatedTracks(
  private val client: LastfmApiClient,
  private val user: String,
  private val period: Period,
) :
  PagingSource<Int, Track>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
    return try {
      val next = params.key ?: 1
      Log.d("Test", next.toString())
      val tracks = client.getTopPeriodTracks(user, page = next, period = period)

      LoadResult.Page(
        data = tracks.tracks,
        prevKey = null,
        nextKey = if (next <= tracks.meta.totalPages) next.plus(1) else null
      )

    } catch (e: Error) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
    return state.anchorPosition
  }
}

class PaginatedArtists(
  private val client: LastfmApiClient,
  private val user: String,
  private val period: Period,
) :
  PagingSource<Int, Artist>() {


  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
    return try {
      val next = params.key ?: 1
      val artists = client.getTopPeriodArtists(user, page = next, period = period)

      LoadResult.Page(
        data = artists.artists,
        prevKey = null,
        nextKey = if (next <= artists.meta.totalPages) next.plus(1) else null
      )

    } catch (e: Error) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Artist>): Int? {
    return state.anchorPosition
  }
}

class PaginatedAlbums(
  private val client: LastfmApiClient,
  private val user: String,
  private val period: Period,
) :
  PagingSource<Int, Album>() {


  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
    return try {
      val next = params.key ?: 1
      val albums = client.getTopPeriodAlbums(user, page = next, period = period)

      LoadResult.Page(
        data = albums.albums,
        prevKey = null,
        nextKey = if (next <= albums.meta.totalPages) next.plus(1) else null
      )

    } catch (e: Error) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
    return state.anchorPosition
  }
}

class PaginatedLovedTracks(
  private val client: LastfmApiClient,
  private val user: String,
) :
  PagingSource<Int, Track>() {


  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Track> {
    return try {
      val next = params.key ?: 1
      val tracks = client.getLovedTracks(user, page = next)

      LoadResult.Page(
        data = tracks.tracks,
        prevKey = null,
        nextKey = if (next <= tracks.meta.totalPages) next.plus(1) else null
      )

    } catch (e: Error) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Track>): Int? {
    return state.anchorPosition
  }
}

class PaginatedFriends(
  private val client: LastfmApiClient,
  private val user: String,
) :
  PagingSource<Int, User>() {


  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
    return try {
      val next = params.key ?: 1
      val friends = client.getFriends(user, page = next)

      LoadResult.Page(
        data = friends.friends,
        prevKey = null,
        nextKey = if (next <= friends.meta.totalPages) next.plus(1) else null
      )

    } catch (e: Error) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, User>): Int? {
    return state.anchorPosition
  }
}