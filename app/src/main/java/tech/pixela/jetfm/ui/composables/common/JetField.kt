package tech.pixela.jetfm.ui.composables.common

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun PreviewJetField() {
    JetField(value = "Test", label = "This is a test", onValueChange = {})
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun JetField(
    value: String, label: String, onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focus: FocusRequester = FocusRequester(),
    isPassword: Boolean = false,
    onNext: (() -> Unit)? = null,
    nextFocus: FocusRequester? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        modifier = modifier.focusRequester(focus),
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.error
        ),
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
            )
        },
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