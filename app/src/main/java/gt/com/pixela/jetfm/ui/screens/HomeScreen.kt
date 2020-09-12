package gt.com.pixela.jetfm.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import gt.com.pixela.jetfm.data.vm.HomeViewModel
import gt.com.pixela.jetfm.ui.composables.home.Dashboard
import gt.com.pixela.jetfm.ui.composables.home.History

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val tabState = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                content = {
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Home) },
                        label = { Text(text = "Home", style = MaterialTheme.typography.body1) },
                        selected = tabState.value == 0,
                        onSelect = { tabState.value = 0 })
//                    BottomNavigationItem(
//                        icon = { Icon(Icons.Filled.Star) },
//                        label = {
//                            Text(
//                                text = "Favourites",
//                                style = MaterialTheme.typography.body1
//                            )
//                        },
//                        selected = tabState.value == 1,
//                        onSelect = { tabState.value = 1 })
                    BottomNavigationItem(
                        icon = { Icon(Icons.TwoTone.Person) },
                        label = { Text(text = "History", style = MaterialTheme.typography.body1) },
                        selected = tabState.value == 2,
                        onSelect = { tabState.value = 2 })
                }
            )
        },
        bodyContent = {
            Crossfade(current = tabState.value) {
                when (it) {
                    0 -> Dashboard(viewModel = viewModel)
//                    1 -> Text(text = "Hola2")
                    2 -> History(viewModel = viewModel)
                    else -> Surface {}
                }
            }
        }
    )
}