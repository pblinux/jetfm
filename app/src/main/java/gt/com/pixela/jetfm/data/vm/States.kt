package gt.com.pixela.jetfm.data.vm

sealed class ResultState {
  object Uninitialized : ResultState()
  object Loading : ResultState()
  data class Error(val error: String) : ResultState()
  data class Refreshing<T>(val current: T) : ResultState()
  data class Loaded<T>(val data: T) : ResultState()
}

sealed class LoginState {
  object Uninitialized : LoginState()
  object Loading : LoginState()
  object Error : LoginState()
  data class Loaded(val username: String, val key: String) : LoginState()
}