package gt.com.pixela.jetfm.data.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gt.com.pixela.jetfm.data.models.*
import gt.com.pixela.jetfm.data.source.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val apiClient: LastfmApiClient,
  private val storeManager: DataStoreManager,
  application: Application,
) : AndroidViewModel(application) {

  init {
    viewModelScope.launch {
      storeManager.user.collect {
        if (!::_user.isInitialized) {
          _user = MutableStateFlow(it)
        }
        _user.emit(it)
      }
    }
  }

  // Selected period
  private val _period = MutableStateFlow(Period.Weekly)
  private val _periodOpen = MutableStateFlow(false)
  val period = _period.asStateFlow()
  val periodDialogOpen = _periodOpen.asStateFlow()

  // JetBar elevated
  private var _lastIndex = 0
  private val _barElevated = MutableStateFlow(false)
  val barElevated = _barElevated.asStateFlow()

  // Current user
  private lateinit var _user: MutableStateFlow<String>

  // Home info
  private val _home = MutableStateFlow<ResultState>(ResultState.Uninitialized)
  val home = _home.asStateFlow()

  // Profile info
  private val _profile = MutableStateFlow<ResultState>(ResultState.Uninitialized)
  val profile = _profile.asStateFlow()

  // History
  var tracks: Flow<PagingData<Track>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedTracks(apiClient, _user.value, _period.value) }
      .flow.cachedIn(viewModelScope)

  var artists: Flow<PagingData<Artist>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedArtists(apiClient, _user.value, _period.value) }
      .flow.cachedIn(viewModelScope)

  var albums: Flow<PagingData<Album>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedAlbums(apiClient, _user.value, _period.value) }
      .flow.cachedIn(viewModelScope)

  val lovedTracks: Flow<PagingData<Track>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedLovedTracks(apiClient, _user.value) }
      .flow.cachedIn(viewModelScope)

  // Profile friends
  val friends: Flow<PagingData<User>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedFriends(apiClient, _user.value) }
      .flow.cachedIn(viewModelScope)


  // Toggle period dialog
  fun togglePeriodDialog() {
    _periodOpen.value = !_periodOpen.value
  }

  // Change period
  fun changePeriod(period: Period) {
    viewModelScope.launch {
      _period.value = period
    }
  }

  // Update bar position
  fun updateBarElevation(index: Int) {
    viewModelScope.launch {
      if (index != _lastIndex) {
        _barElevated.value = index > _lastIndex
      } else {
        _barElevated.value = false
      }
    }
  }

  // Get home screen info
  fun getHome(refreshing: Boolean = false) {
    viewModelScope.launch {
      try {
        val user = _user.value
        if (refreshing)
          _home.emit(ResultState.Refreshing((_home.value as ResultState.Loaded<*>).data))
        else
          _home.emit(ResultState.Loading)
        coroutineScope {
          val recentTracks = async { apiClient.getRecentTracks(user) }
          val topWeeklyArtists = async { apiClient.getTopPeriodArtists(user) }
          val topWeeklyAlbums = async { apiClient.getTopPeriodAlbums(user) }
          val topWeeklyTracks = async { apiClient.getTopPeriodTracks(user) }
          _home.emit(
            ResultState.Loaded(
              HomeInfo(
                recentTracks = recentTracks.await(),
                topAlbums = topWeeklyAlbums.await(),
                topArtists = topWeeklyArtists.await(),
                topTracks = topWeeklyTracks.await()
              )
            )
          )
        }
      } catch (e: Error) {
        Log.e("JetFM", e.message.toString())
        _home.emit(ResultState.Error(e.message.toString()))
      }
    }
  }

  // Get profile screen info
  fun getProfile() {
    viewModelScope.launch {
      try {
        _profile.emit(ResultState.Loading)
        coroutineScope {
          val userInfo = async { apiClient.getInfo(_user.value) }
          _profile.emit(ResultState.Loaded(ProfileInfo(userInfo.await())))
        }
      } catch (e: Error) {
        _profile.emit(ResultState.Error(e.message.toString()))
      }
    }
  }

  // Reload tracks when period changed
  fun reloadTracks() {
    tracks =
      Pager(PagingConfig(pageSize = 10)) { PaginatedTracks(apiClient, _user.value, _period.value) }
        .flow.cachedIn(viewModelScope)
  }

  // Reload tracks when period changed
  fun reloadArtists() {
    artists =
      Pager(PagingConfig(pageSize = 10)) { PaginatedArtists(apiClient, _user.value, _period.value) }
        .flow.cachedIn(viewModelScope)
  }

  // Reload tracks when period changed
  fun reloadAlbums() {
    albums =
      Pager(PagingConfig(pageSize = 10)) { PaginatedAlbums(apiClient, _user.value, _period.value) }
        .flow.cachedIn(viewModelScope)
  }
}