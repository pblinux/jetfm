package tech.pixela.jetfm.data.source.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {
    private val dataStore = context.dataStore

    val user: Flow<String> = dataStore.data.map { store ->
        store[DataStoreKeys.USER] ?: ""
    }

    val loggedIn: Flow<Boolean> = dataStore.data.map { store ->
        val user = store[DataStoreKeys.USER]
        user != null
    }

    suspend fun onLogin(user: String, token: String) {
        dataStore.edit { store ->
            store[DataStoreKeys.USER] = user
            store[DataStoreKeys.TOKEN] = token
        }
    }
}
