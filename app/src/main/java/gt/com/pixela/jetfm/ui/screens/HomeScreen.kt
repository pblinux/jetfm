package gt.com.pixela.jetfm.ui.screens

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(user: String, key: String) {
    Column() {
        Text(text = "Hola")
        Text(text = "Home")
    }
}