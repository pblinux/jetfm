package gt.com.pixela.jetfm.ui.composables.login

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
    ) {
        Text(
            text = "Welcome to Jetfm",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = userState.value,
            onValueChange = { userState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = "Username",
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = passState.value,
            onValueChange = { passState.value = it },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            label = {
                Text(
                    text = "Password",
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            })
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { onPressed(userState.value, passState.value) }) {
            when (currentState) {
                LoginState.Loading -> CircularProgressIndicator(color = Color.White)
                else -> Text(text = "Log In")
            }
        }
    }
}