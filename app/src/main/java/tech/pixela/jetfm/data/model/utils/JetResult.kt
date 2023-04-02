package tech.pixela.jetfm.data.model.utils

sealed class JetResult<out T> {
    data class Error(val message: String) : JetResult<Nothing>()
    data class Refresh<T>(val current: T) : JetResult<T>()
    data class Loaded<T>(val data: T) : JetResult<T>()
    object Empty : JetResult<Nothing>()
    object Fresh : JetResult<Nothing>()
    object Loading : JetResult<Nothing>()
}
