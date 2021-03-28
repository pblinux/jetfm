package gt.com.pixela.jetfm.ui.composables.login

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.ui.composables.common.MediumGap
import gt.com.pixela.jetfm.ui.theme.lightBlack
import gt.com.pixela.jetfm.ui.theme.primaryRed
import gt.com.pixela.jetfm.ui.theme.secondaryRed
import gt.com.pixela.jetfm.ui.theme.white
import gt.com.pixela.jetfm.utils.LocalLoginViewModel

@Preview
@Composable
fun LoginForm() {
  val loginViewModel = LocalLoginViewModel.current

  val text1 by loginViewModel.username.collectAsState()
  val text2 by loginViewModel.password.collectAsState()

  Column {
    JetField(
      value = text1,
      onValueChange = { loginViewModel.updateUsername(it) },
      label = stringResource(id = R.string.username_label),
      modifier = Modifier.fillMaxWidth()
    )
    MediumGap()
    JetField(
      value = text2,
      onValueChange = { loginViewModel.updatePassword(it) },
      label = stringResource(id = R.string.password_label),
      modifier = Modifier.fillMaxWidth(),
      isPassword = true
    )
  }
}

@Composable
fun JetField(
  value: String, label: String, onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  isPassword: Boolean = false
) {
  TextField(
    value = value,
    modifier = modifier,
    onValueChange = onValueChange,
    label = { Text(label, style = MaterialTheme.typography.caption.copy(color = primaryRed)) },
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = lightBlack,
      textColor = white,
      focusedIndicatorColor = secondaryRed,
      cursorColor = white,
    ),
    visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text)
  )
}

//@Composable
//fun JetField(
//  value: String,
//  label: String,
//  onValueChange: (String) -> Unit,
//  isPassword: Boolean = false
//) {
//  TextField(
//    value = value,
//    onValueChange = { Log.d("Hola", it.text); onValueChange(it.text) },
//    label = { Text(label) },
//    modifier = Modifier.fillMaxWidth(),
//    visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
//    colors = TextFieldDefaults.textFieldColors(
//      backgroundColor = lightBlack,
//      textColor = white,
//      focusedIndicatorColor = secondaryRed,
//      cursorColor = white,
//    ),
//    keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text)
//  )
//}