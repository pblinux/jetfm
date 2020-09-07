package gt.com.pixela.jetfm.ui.screens

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.preference.PreferenceManager
import gt.com.pixela.jetfm.data.vm.LoginState
import gt.com.pixela.jetfm.data.vm.LoginViewModel
import gt.com.pixela.jetfm.ui.composables.login.LoginForm


@Composable
fun LoginScreen(onLoggedIn: (String, String) -> Unit, viewModel: LoginViewModel) {
    val context = ContextAmbient.current

    fun saveState(username: String, key: String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        with(preferences.edit()) {
            putString("user", username)
            putString("key", key)
            commit()
        }
    }

    viewModel.currentState.observeForever {
        when (it) {
            is LoginState.Loaded -> {
                saveState(it.username, it.key)
                onLoggedIn(it.username, it.key)
            }
            else -> {
                //
            }
        }
    }

    Scaffold(
        bodyContent = {
            LoginForm(viewModel)
        }
    )
}




