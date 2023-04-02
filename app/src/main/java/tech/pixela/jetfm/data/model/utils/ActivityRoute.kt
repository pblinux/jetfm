package tech.pixela.jetfm.data.model.utils

import androidx.annotation.StringRes
import tech.pixela.jetfm.R

sealed class ActivityRoute(@StringRes val screenResId: Int) {
    object Tracks : ActivityRoute(R.string.tracks)
    object Artists : ActivityRoute(R.string.artists)
    object Albums : ActivityRoute(R.string.albums)
    object LovedTracks : ActivityRoute(R.string.loved_tracks)
}
