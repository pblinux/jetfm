package gt.com.pixela.jetfm.ui.route

import androidx.compose.runtime.Composable
import com.github.zsoltk.compose.router.Router
import gt.com.pixela.jetfm.ui.screens.HomeScreen

interface LoggedIn {
    sealed class InRouting {
        object Home : InRouting()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: InRouting, user: String, key: String) {
            Router(defaultRouting = defaultRouting) {
                when (val current = it.last()) {
                    is InRouting.Home -> HomeScreen(user, key)
                }
            }
        }
    }
}