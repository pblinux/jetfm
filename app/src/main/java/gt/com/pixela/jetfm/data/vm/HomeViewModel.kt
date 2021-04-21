package gt.com.pixela.jetfm.data.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import gt.com.pixela.jetfm.data.models.HomeInfo
import gt.com.pixela.jetfm.data.source.LastfmApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ResultState {
  object Uninitialized : ResultState()
  object Loading : ResultState()
  object Error : ResultState()
  data class Loaded(val data: Any) : ResultState()
}

class HomeViewModel(application: Application) : AndroidViewModel(application) {
  // LastFM data source
  private val api = LastfmApiClient()

  // JetBar elevated
  private var _lastIndex = 0
  private val _barElevated = MutableStateFlow(false)
  val barElevated = _barElevated.asStateFlow()

  // Home info
  private val _home = MutableStateFlow<ResultState>(ResultState.Uninitialized)
  val home = _home.asStateFlow()

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
  fun getHome() {
    viewModelScope.launch {
      try {
        val user = getUser()
        _home.emit(ResultState.Loading)
        val recentTracks = api.getRecentTracks(user)
        val topWeeklyArtists = api.getTopPeriodArtists(user)
        val topWeeklyAlbums = api.getTopPeriodAlbums(user)
        val topWeeklyTracks = api.getTopPeriodTracks(user)
        _home.emit(
          ResultState.Loaded(
            HomeInfo(
              recentTracks = recentTracks,
              topAlbums = topWeeklyAlbums,
              topArtists = topWeeklyArtists,
              topTracks = topWeeklyTracks
            )
          )
        )
      } catch (e: Error) {
        _home.emit(ResultState.Error)
        Log.d("Jetfm", e.message ?: "Error")
      }
    }
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