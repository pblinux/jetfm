package gt.com.pixela.jetfm.data.vm

sealed class ResultState {
  object Uninitialized : ResultState()
  object Loading : ResultState()
  object Error : ResultState()
  data class Refreshing(val current: Any) : ResultState()
  data class Loaded(val data: Any) : ResultState()
}

sealed class LoginState {
  object Uninitialized : LoginState()
  object Loading : LoginState()
  object Error : LoginState()
  data class Loaded(val username: String, val key: String) : LoginState()
}