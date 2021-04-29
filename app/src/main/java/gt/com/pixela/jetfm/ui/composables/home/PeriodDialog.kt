package gt.com.pixela.jetfm.ui.composables.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import gt.com.pixela.jetfm.data.source.Period
import gt.com.pixela.jetfm.ui.theme.primaryRed
import gt.com.pixela.jetfm.utils.LocalHomeViewModel

@Composable
fun PeriodDialog(onDismiss: () -> Unit) {
  val homeViewModel = LocalHomeViewModel.current
  var current by remember { mutableStateOf(homeViewModel.period.value) }

  AlertDialog(onDismissRequest = onDismiss,
    title = { Text(text = "Choose a period") },
    text = {
      Column(Modifier.padding(top = 8.dp)) {
        Period.values().forEach {
          PeriodDialogItem(isSelected = it == current, text = it.name) { current = it }
        }
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (current != homeViewModel.period.value) homeViewModel.changePeriod(current)
          onDismiss()
        }, colors = ButtonDefaults.buttonColors(
          backgroundColor = Color.Transparent,
          contentColor = primaryRed
        ), elevation = ButtonDefaults.elevation(0.dp)
      ) { Text("OK") }
    },
    dismissButton = {
      Button(
        onClick = onDismiss, colors = ButtonDefaults.buttonColors(
          backgroundColor = Color.Transparent,
          contentColor = primaryRed
        ), elevation = ButtonDefaults.elevation(0.dp)
      ) { Text("Cancel") }
    }
  )
}

@Composable
fun PeriodDialogItem(isSelected: Boolean, text: String, onClick: () -> Unit) {
  Row(
    Modifier
      .fillMaxWidth()
      .height(56.dp)
      .selectable(selected = isSelected, onClick = onClick, role = Role.RadioButton)
  ) {
    RadioButton(selected = isSelected, onClick = null)
    Text(
      text = text,
      style = MaterialTheme.typography.body1.merge(),
      modifier = Modifier.padding(start = 16.dp)
    )
  }
}