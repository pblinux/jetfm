package gt.com.pixela.jetfm.data.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import gt.com.pixela.jetfm.data.models.*
import gt.com.pixela.jetfm.data.source.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ResultState {
  object Uninitialized : ResultState()
  object Loading : ResultState()
  object Refreshing : ResultState()
  object Error : ResultState()
  data class Loaded(val data: Any) : ResultState()
}

class HomeViewModel(application: Application) : AndroidViewModel(application) {
  // LastFM data source
  private val api = LastfmApiClient()

  // Selected period
  private val _period = MutableStateFlow(Period.Weekly)
  private val _periodOpen = MutableStateFlow(false)
  val period = _period.asStateFlow()
  val periodDialogOpen = _periodOpen.asStateFlow()

  // JetBar elevated
  private var _lastIndex = 0
  private val _barElevated = MutableStateFlow(false)
  val barElevated = _barElevated.asStateFlow()

  // Home info
  private val _home = MutableStateFlow<ResultState>(ResultState.Uninitialized)
  val home = _home.asStateFlow()

  // Profile info
  private val _profile = MutableStateFlow<ResultState>(ResultState.Uninitialized)
  val profile = _profile.asStateFlow()

  // History
  var tracks: Flow<PagingData<Track>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedTracks(api, getUser(), _period.value) }
      .flow.cachedIn(viewModelScope)

  var artists: Flow<PagingData<Artist>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedArtists(api, getUser(), _period.value) }
      .flow.cachedIn(viewModelScope)

  var albums: Flow<PagingData<Album>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedAlbums(api, getUser(), _period.value) }
      .flow.cachedIn(viewModelScope)

  val lovedTracks: Flow<PagingData<Track>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedLovedTracks(api, getUser()) }
      .flow.cachedIn(viewModelScope)

  // Profile friends
  val friends: Flow<PagingData<User>> =
    Pager(PagingConfig(pageSize = 10)) { PaginatedFriends(api, getUser()) }
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
        val user = getUser()
        if (refreshing)
          _home.emit(ResultState.Refreshing)
        else
          _home.emit(ResultState.Loading)
        coroutineScope {
          val recentTracks = async { api.getRecentTracks(user) }
          val topWeeklyArtists = async { api.getTopPeriodArtists(user) }
          val topWeeklyAlbums = async { api.getTopPeriodAlbums(user) }
          val topWeeklyTracks = async { api.getTopPeriodTracks(user) }
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
        _home.emit(ResultState.Error)
      }
    }
  }

  // Get profile screen info
  fun getProfile() {
    viewModelScope.launch {
      try {
        _profile.emit(ResultState.Loading)
        coroutineScope {
          val userInfo = async { api.getInfo(getUser()) }
          _profile.emit(ResultState.Loaded(ProfileInfo(userInfo.await())))
        }
      } catch (e: Error) {
        _profile.emit(ResultState.Error)
      }
    }
  }

  // Reload tracks when period changed
  fun reloadTracks() {
    tracks =
      Pager(PagingConfig(pageSize = 10)) { PaginatedTracks(api, getUser(), _period.value) }
        .flow.cachedIn(viewModelScope)
  }

  // Reload tracks when period changed
  fun reloadArtists() {
    artists =
      Pager(PagingConfig(pageSize = 10)) { PaginatedArtists(api, getUser(), _period.value) }
        .flow.cachedIn(viewModelScope)
  }

  // Reload tracks when period changed
  fun reloadAlbums() {
    albums =
      Pager(PagingConfig(pageSize = 10)) { PaginatedAlbums(api, getUser(), _period.value) }
        .flow.cachedIn(viewModelScope)
  }

  // Get current user
  private fun getUser(): String {
    return with(sharedPreferences) { getString("user", "") ?: run { "" } }
  }

  // Our shared preference
  private val sharedPreferences by lazy {
    val key = MasterKey.Builder(application)
      .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    EncryptedSharedPreferences.create(
      application, "jetfm_secret_preferences", key,
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
  }
}