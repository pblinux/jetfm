package tech.pixela.jetfm.ui.composables.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import tech.pixela.jetfm.R
import tech.pixela.jetfm.ui.composables.common.JetField
import tech.pixela.jetfm.ui.composables.common.MediumJetSpace

@Composable
fun LoginForm(
    username: String,
    password: String,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    val passwordFocus by remember { mutableStateOf(FocusRequester()) }

    Column {
        JetField(
            value = username,
            onValueChange = { onUsernameChange(it) },
            label = stringResource(id = R.string.username_label),
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding(),
            onNext = { passwordFocus.requestFocus() },
            nextFocus = passwordFocus
        )
        MediumJetSpace()
        JetField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            label = stringResource(id = R.string.password_label),
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding(),
            isPassword = true,
            focus = passwordFocus
        )
    }
}
