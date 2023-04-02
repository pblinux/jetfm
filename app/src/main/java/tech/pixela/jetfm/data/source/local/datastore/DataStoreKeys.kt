package tech.pixela.jetfm.data.source.local.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val USER = stringPreferencesKey("user")
    val TOKEN = stringPreferencesKey("token")
}
