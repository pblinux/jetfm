package tech.pixela.jetfm.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.insets.ProvideWindowInsets
import tech.pixela.jetfm.ui.composables.animations.JetCircles
import tech.pixela.jetfm.ui.composables.common.*
import tech.pixela.jetfm.R
import tech.pixela.jetfm.data.model.utils.JetResult
import tech.pixela.jetfm.ui.activities.login.LoginViewModel
import tech.pixela.jetfm.ui.composables.login.LoginForm

@Preview
@Composable
private fun PreviewLogin() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginBase(
        currentUsername = username,
        currentPassword = password,
        isLoading = false,
        onLogin = { },
        onUsernameChanged = { username = it },
        onPasswordChanged = { password = it }
    )
}

@Composable
fun Login(
    loginViewModel: LoginViewModel = viewModel()
) {
    val loginState by loginViewModel.loginState.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        LoginBase(
            currentUsername = username,
            currentPassword = password,
            isLoading = loginState is JetResult.Loading,
            onLogin = { loginViewModel.login(username, password) },
            onUsernameChanged = { username = it },
            onPasswordChanged = { password = it }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginBase(
    currentUsername: String,
    currentPassword: String,
    isLoading: Boolean,
    onLogin: () -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                JetTitle()
                SmallJetSpace()
                JetLine()
                LargeJetSpace()
                LoginForm(
                    username = currentUsername,
                    password = currentPassword,
                    onUsernameChange = onUsernameChanged,
                    onPasswordChange = onPasswordChanged,
                )
                LargeJetSpace()
                JetButton(
                    title = stringResource(id = R.string.enter_label),
                    isLoading = isLoading,
                    onClick = { onLogin() },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            JetCircles(
                size = 250, modifier = Modifier
                    .alpha(0.75f)
                    .align(alignment = Alignment.BottomEnd)
                    .offset(100.dp, 50.dp)
            )

//            JetCircles(
//                size = 250, modifier = Modifier
//                    .alpha(0.50f)
//                    .align(alignment = Alignment.TopStart)
//                    .offset((-50).dp, (-25).dp)
//            )
        }
    }
}