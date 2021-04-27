package gt.com.pixela.jetfm.ui.composables.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.ui.composables.common.MediumGap
import gt.com.pixela.jetfm.ui.theme.lightBlack
import gt.com.pixela.jetfm.ui.theme.primaryRed
import gt.com.pixela.jetfm.ui.theme.secondaryRed
import gt.com.pixela.jetfm.ui.theme.white
import gt.com.pixela.jetfm.utils.LocalLoginViewModel

@ExperimentalComposeUiApi
@Preview
@Composable
fun LoginForm() {
  val loginViewModel = LocalLoginViewModel.current

  val userText by loginViewModel.username.collectAsState()
  val passwordText by loginViewModel.password.collectAsState()

  val passwordFocus by remember { mutableStateOf(FocusRequester()) }

  Column {
    JetField(
      value = userText,
      onValueChange = { loginViewModel.updateUsername(it) },
      label = stringResource(id = R.string.username_label),
      modifier = Modifier.fillMaxWidth(),
      onNext = { passwordFocus.requestFocus() },
      nextFocus = passwordFocus
    )
    MediumGap()
    JetField(
      value = passwordText,
      onValueChange = { loginViewModel.updatePassword(it) },
      label = stringResource(id = R.string.password_label),
      modifier = Modifier.fillMaxWidth(),
      isPassword = true,
      focus = passwordFocus
    )
  }
}

@ExperimentalComposeUiApi
@Composable
fun JetField(
  modifier: Modifier = Modifier,
  focus: FocusRequester = FocusRequester(),
  isPassword: Boolean = false,
  onNext: (() -> Unit)? = null,
  nextFocus: FocusRequester? = null,
  value: String, label: String, onValueChange: (String) -> Unit,
) {
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = value,
    modifier = modifier.focusRequester(focus),
    onValueChange = onValueChange,
    label = { Text(label, style = MaterialTheme.typography.caption.copy(color = primaryRed)) },
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = lightBlack,
      textColor = white,
      focusedIndicatorColor = secondaryRed,
      cursorColor = white,
    ),
    visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    keyboardActions = KeyboardActions(
      onNext = { if (nextFocus != null && onNext != null) onNext() },
      onDone = { keyboardController?.hide() }
    ),
    keyboardOptions = KeyboardOptions(
      keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
      imeAction = if (onNext != null) ImeAction.Next else ImeAction.Done
    )
  )
}