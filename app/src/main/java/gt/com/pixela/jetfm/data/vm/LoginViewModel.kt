package gt.com.pixela.jetfm.data.vm

import android.app.Application
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import gt.com.pixela.jetfm.data.source.LastfmApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class LoginState {
  object Uninitialized : LoginState()
  object Loading : LoginState()
  object Error : LoginState()
  data class Loaded(val username: String, val key: String) : LoginState()
}

class LoginViewModel(application: Application) : AndroidViewModel(application) {
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
    Log.d("jetfm", username)
    _username.value = username
  }

  fun updatePassword(password: String) {
    _password.value = password
  }

  fun login() {
    viewModelScope.launch {
      _state.emit(LoginState.Loading)
      val result = LastfmApiClient().login(_username.value, _password.value)
      result?.let {
        _state.emit(LoginState.Loaded(it.session.name, it.session.key))
      } ?: run { _state.emit(LoginState.Error) }
    }
  }

  fun saveSession(username: String, key: String) {
    val preferences = EncryptedSharedPreferences.create(
      "jetfm_secret_preferences",
      MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
      getApplication(),
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    with(preferences.edit()) {
      putString("user", username)
      putString("key", key)
      commit()
    }
  }
}