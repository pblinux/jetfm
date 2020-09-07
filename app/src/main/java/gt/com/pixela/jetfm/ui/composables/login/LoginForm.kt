package gt.com.pixela.jetfm.ui.composables.login

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import gt.com.pixela.jetfm.data.vm.LoginState
import gt.com.pixela.jetfm.data.vm.LoginViewModel

@Composable
fun LoginForm(viewModel: LoginViewModel) {

    val currentState by viewModel.currentState.observeAsState(initial = LoginState.Uninitialized)
    val passState = remember { mutableStateOf("") }
    val userState = remember { mutableStateOf("") }


    fun onPressed(username: String, password: String) {
        viewModel.login(username, password)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(text = "Welcome")
        Box(paddingBottom = 12.dp)
        TextField(
            value = userState.value,
            onValueChange = { userState.value = it },
            label = { Text(text = "Username") })
        Box(paddingBottom = 8.dp)
        TextField(
            value = passState.value,
            onValueChange = { passState.value = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Password") })
        Box(paddingBottom = 16.dp)
        Button(onClick = { onPressed(userState.value, passState.value) }) {
            when (currentState) {
                LoginState.Loading -> CircularProgressIndicator()
                else -> Text(text = "Log In")
            }
        }
    }
}