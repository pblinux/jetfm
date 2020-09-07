package gt.com.pixela.jetfm.ui.screens

import android.util.Log
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import gt.com.pixela.jetfm.data.source.LastfmApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(user: String, key: String) {
    Column() {
        Text(text = "Hola")
        Text(text = "Home")
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                val userResult = LastfmApiClient().getInfo(user)
                val tracksResult = LastfmApiClient().getRecentTracks(user)
                userResult?.let { Log.d("Jetfm", userResult.realname) }
                Log.d("Jetfm", tracksResult.first().artist.name)

            }
        }) {
            Text(text = "Probando")
        }
    }
}