package tech.pixela.jetfm.ui.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tech.pixela.jetfm.data.model.lastfm.base.*
import tech.pixela.jetfm.data.model.lastfm.common.Period
import tech.pixela.jetfm.data.model.lastfm.holders.HomeHolder
import tech.pixela.jetfm.data.model.lastfm.result.UserResult
import tech.pixela.jetfm.data.model.utils.JetResult
import tech.pixela.jetfm.data.repository.LastfmRepository
import tech.pixela.jetfm.data.source.local.datastore.DataStoreManager
import tech.pixela.jetfm.data.source.remote.paging.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val lastfmRepository: LastfmRepository,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    init {
        viewModelScope.launch {
            dataStoreManager.user.collect {
                if (!::_user.isInitialized) {
                    _user = MutableStateFlow(it)
                }
                _user.emit(it)
                getHomeInfo()
                getProfile()
                updateActivity()
            }
        }
    }

    /* Current user and profile */
    private lateinit var _user: MutableStateFlow<String>
    private val _userInfo: MutableStateFlow<JetResult<UserResult>> =
        MutableStateFlow(JetResult.Fresh)
    val userInfo = _userInfo.asStateFlow()

    fun getProfile(refresh: Boolean = false) {
        viewModelScope.launch {
            updateFriends()
            lastfmRepository.getProfile(
                _user.value,
                refresh = refresh,
                previousState = if (refresh) (_userInfo.value as JetResult.Loaded).data else null
            ).collect { _userInfo.emit(it) }
        }
    }

    // Profile
    lateinit var friends: Flow<PagingData<Friend>>

    private fun updateFriends() {
        friends = Pager(PagingConfig(pageSize = 10)) {
            PagingFriendsSource(lastfmRepository.lastfmSource, _user.value)
        }.flow.cachedIn(viewModelScope)
    }

    /* Home information */
    private val _homeInfo = MutableStateFlow<JetResult<HomeHolder>>(JetResult.Fresh)
    val homeInfo = _homeInfo.asStateFlow()

    // Call to fetch all the home information
    fun getHomeInfo(refresh: Boolean = false) {
        viewModelScope.launch {
            lastfmRepository.getHome(
                _user.value,
                refresh = refresh,
                previousState = if (refresh) (_homeInfo.value as JetResult.Loaded).data else null
            ).collect { _homeInfo.emit(it) }
        }
    }

    /* Activity paginated */
    // Tracks
    lateinit var tracks: Flow<PagingData<Track>>

    // Artists
    lateinit var artists: Flow<PagingData<Artist>>

    // Albums
    lateinit var albums: Flow<PagingData<Album>>

    // Loved tracks
    lateinit var lovedTracks: Flow<PagingData<Track>>

    private val _currentPeriod = MutableStateFlow(Period.Weekly)
    val currentPeriod = _currentPeriod.asStateFlow()

    private val _showPeriodDialog = MutableStateFlow(false)
    val showPeriodDialog = _showPeriodDialog.asStateFlow()

    fun updatePeriod(period: Period) {
        viewModelScope.launch { _currentPeriod.emit(period) }
        updateActivity()
    }

    fun toggleDialog() {
        viewModelScope.launch { _showPeriodDialog.emit(!_showPeriodDialog.value) }
    }

    private fun updateActivity() {
        tracks =
            Pager(PagingConfig(pageSize = 10)) {
                PagingTracksSource(lastfmRepository.lastfmSource, _user.value, _currentPeriod.value)
            }.flow.cachedIn(viewModelScope)

        artists =
            Pager(PagingConfig(pageSize = 10)) {
                PagingArtistsSource(
                    lastfmRepository.lastfmSource, _user.value, _currentPeriod.value
                )
            }.flow.cachedIn(viewModelScope)

        albums =
            Pager(PagingConfig(pageSize = 10)) {
                PagingAlbumsSource(lastfmRepository.lastfmSource, _user.value, _currentPeriod.value)
            }.flow.cachedIn(viewModelScope)

        lovedTracks =
            Pager(PagingConfig(pageSize = 10)) {
                PagingLovedTracksSource(lastfmRepository.lastfmSource, _user.value)
            }.flow.cachedIn(viewModelScope)
    }
}