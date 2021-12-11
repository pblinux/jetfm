package tech.pixela.jetfm.ui.composables.home.activity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import tech.pixela.jetfm.R
import tech.pixela.jetfm.data.model.lastfm.common.Period
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.common.SmallJetSpace

@Composable
fun PeriodDialog(
    open: Boolean = false,
    onDismiss: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    var currentPeriod by remember { mutableStateOf(mainViewModel.currentPeriod.value) }

    if (open) {
        AlertDialog(
            title = { Text(text = stringResource(id = R.string.choose_period)) },
            text = {
                Column() {
                    Period.values().forEach {
                        PeriodItem(selected = it == currentPeriod, text = it.name) {
                            currentPeriod = it
                        }
                    }
                }
            },
            confirmButton = {
                FilledTonalButton(onClick = {
                    if (currentPeriod != mainViewModel.currentPeriod.value) {
                        mainViewModel.updatePeriod(currentPeriod)
                    }
                    onDismiss()
                }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            },
            onDismissRequest = {}
        )
    }

}

@Composable
fun PeriodItem(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
            .selectable(selected = selected, onClick = onClick, role = Role.RadioButton)
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary
            )
        )
        SmallJetSpace()
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}