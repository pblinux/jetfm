package gt.com.pixela.jetfm.data.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import gt.com.pixela.jetfm.MainActivity
import gt.com.pixela.jetfm.data.models.Meta
import gt.com.pixela.jetfm.data.models.Track
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

sealed class TracksState {
    object Uninitialized : TracksState()
    object Loading : TracksState()
    object Error : TracksState()
    data class Loaded(val tracks: List<Track>) : TracksState()
}

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val api = LastfmApiClient()

    private val userInfo = MutableLiveData<ResultState>()
    private val recentTracks = MutableLiveData<ResultState>()
    private val historyTracks = MutableLiveData<TracksState>()

    val currentInfo: LiveData<ResultState> get() = userInfo
    val currentRecentTracks: LiveData<ResultState> get() = recentTracks
    val currentHistoryTracks: LiveData<TracksState> get() = historyTracks

    var currentHistoryMeta: Meta? = null

    fun getInfo() {
        getApplication<Application>().applicationContext
        CoroutineScope(Dispatchers.IO).launch {
            userInfo.postValue(ResultState.Loading)
            val result = api.getInfo(getUser())
            result?.let {
                userInfo.postValue(ResultState.Loaded(it))
            } ?: run { userInfo.postValue(ResultState.Error) }
        }
    }

    fun getRecentTracks() {
        CoroutineScope(Dispatchers.IO).launch {
            recentTracks.postValue(ResultState.Loading)
            val result = api.getRecentTracks(getUser())
            recentTracks.postValue(
                ResultState.Loaded(
                    result?.tracks
                        ?: run { listOf<Track>() })
            )
        }
    }

    fun getHistoryTracks() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = api.getRecentTracks(
                getUser(),
                page = currentHistoryMeta?.let { currentHistoryMeta!!.page + 1 } ?: run { 1 })
            val tracks = when (historyTracks.value) {
                is TracksState.Loaded -> {
                    (historyTracks.value as TracksState.Loaded).tracks.toMutableList()
                }
                else -> {
                    mutableListOf()
                }
            }
            result?.let { tracks.addAll(it.tracks); currentHistoryMeta = it.meta }
            historyTracks.postValue(
                TracksState.Loaded(tracks)
            )
        }
    }

    private fun getUser(): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(getApplication())
        return with(preferences) { getString("user", "") ?: run { "" } }
    }

}