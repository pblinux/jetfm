package gt.com.pixela.jetfm.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gt.com.pixela.jetfm.data.source.DataStoreManager
import gt.com.pixela.jetfm.data.source.LastfmApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val apiClient: LastfmApiClient,
  private val storeManager: DataStoreManager,
) : ViewModel() {

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
        val session = async { saveSession(it.session.name, it.session.key) }
        session.await()
        _state.emit(LoginState.Loaded(it.session.name, it.session.key))
      } ?: run { _state.emit(LoginState.Error) }
    }
  }

  private suspend fun saveSession(username: String, key: String) {
    storeManager.onLogin(username, key)
  }
}