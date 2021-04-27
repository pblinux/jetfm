package gt.com.pixela.jetfm.data.source

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {
  private val dataStore = context.dataStore

  val user: Flow<String> = dataStore.data.map { store ->
    store[StoreKeys.USER] ?: ""
  }

  val loggedIn: Flow<Boolean> = dataStore.data.map { store ->
    val user = store[StoreKeys.USER]
    user != null
  }

  suspend fun onLogin(user: String, token: String) {
    dataStore.edit { store ->
      store[StoreKeys.USER] = user
      store[StoreKeys.TOKEN] = token
    }
  }
}

private object StoreKeys {
  val USER = stringPreferencesKey("user")
  val TOKEN = stringPreferencesKey("token")
}

private val Context.dataStore by preferencesDataStore("JetFM")
