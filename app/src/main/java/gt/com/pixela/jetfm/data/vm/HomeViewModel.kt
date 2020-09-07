package gt.com.pixela.jetfm.data.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gt.com.pixela.jetfm.data.source.LastfmApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

sealed class ResultState {
    object Uninitialized : ResultState()
    object Loading : ResultState()
    object Error : ResultState()
    data class Loaded(val data: Any) : ResultState()
}

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val api = LastfmApiClient()

    private val userInfo = MutableLiveData<ResultState>()
    private val recentTracks = MutableLiveData<ResultState>()

    val currentInfo: LiveData<ResultState> get() = userInfo
    val currentRecentTracks: LiveData<ResultState> get() = recentTracks

    fun getInfo(user: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userInfo.postValue(ResultState.Loading)
            val result = api.getInfo(user)
            result?.let {
                userInfo.postValue(ResultState.Loaded(it))
            } ?: run { userInfo.postValue(ResultState.Error) }
        }
    }

    fun getRecentTracks(user: String) {
        CoroutineScope(Dispatchers.IO).launch {
            userInfo.postValue(ResultState.Loading)
            val result = api.getRecentTracks(user)
            userInfo.postValue(ResultState.Loaded(result))
        }
    }

}