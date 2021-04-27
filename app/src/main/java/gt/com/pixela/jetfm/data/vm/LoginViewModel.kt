package gt.com.pixela.jetfm.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gt.com.pixela.jetfm.data.source.DataStoreManager
import gt.com.pixela.jetfm.data.source.LastfmApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val apiClient: LastfmApiClient,
  private val storeManager: DataStoreManager,
  application: Application
) : AndroidViewModel(application) {

  // For UI state handling
  private val _state = MutableStateFlow<LoginState>(LoginState.Uninitialized)
  val currentState: StateFlow<LoginState> = _state.asStateFlow()

  // For username handling
  private val _username = MutableStateFlow("")
  val username: StateFlow<String> = _username.asStateFlow()

  // For password handling
  private val _password = MutableStateFlow<String>("")
  val password: StateFlow<String> = _password.asStateFlow()

  fun updateUsername(username: String) {
    _username.value = username
  }

  fun updatePassword(password: String) {
    _password.value = password
  }

  fun login() {
    viewModelScope.launch {
      _state.emit(LoginState.Loading)
      val result = apiClient.login(_username.value, _password.value)
      result?.let {
        _state.emit(LoginState.Loaded(it.session.name, it.session.key))
      } ?: run { _state.emit(LoginState.Error) }
    }
  }

  fun saveSession(username: String, key: String) {
    viewModelScope.launch {
      storeManager.onLogin(username, key)
    }
//    val preferences = EncryptedSharedPreferences.create(
//      "jetfm_secret_preferences",
//      MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
//      getApplication(),
//      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//    )
//
//    with(preferences.edit()) {
//      putString("user", username)
//      putString("key", key)
//      commit()
//    }
  }
}