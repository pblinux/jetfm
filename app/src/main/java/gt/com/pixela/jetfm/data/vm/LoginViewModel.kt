package gt.com.pixela.jetfm.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gt.com.pixela.jetfm.data.source.LastfmApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class LoginState {
    object Uninitialized : LoginState()
    object Loading : LoginState()
    object Error : LoginState()
    data class Loaded(val username: String, val key: String) : LoginState()
}

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private var state = MutableLiveData<LoginState>()
    val currentState: LiveData<LoginState> get() = state

    fun login(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            state.postValue(LoginState.Loading)
            val result = LastfmApiClient().login(username, password)
            result?.let {
                state.postValue(LoginState.Loaded(it.session.name, it.session.key))
            } ?: run { state.postValue(LoginState.Error) }
        }
    }
}