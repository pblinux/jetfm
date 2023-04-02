package tech.pixela.jetfm.ui.activities.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tech.pixela.jetfm.data.model.lastfm.result.LoginResult
import tech.pixela.jetfm.data.model.utils.JetResult
import tech.pixela.jetfm.data.repository.LastfmRepository
import tech.pixela.jetfm.data.source.local.datastore.DataStoreManager
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val lastfmRepository: LastfmRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    // Login state handler
    private val _loginState = MutableStateFlow<JetResult<LoginResult>>(JetResult.Fresh)
    val loginState = _loginState.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            lastfmRepository.login(username, password).collect {
                _loginState.emit(it)
                if (it is JetResult.Loaded) {
                    saveSession(it.data.session.name, it.data.session.key)
                }
            }
        }
    }

    private suspend fun saveSession(username: String, key: String) {
        dataStoreManager.onLogin(username, key)
    }
}
