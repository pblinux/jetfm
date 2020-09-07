package gt.com.pixela.jetfm.ui.route

import androidx.compose.runtime.Composable
import com.github.zsoltk.compose.router.Router
import gt.com.pixela.jetfm.data.vm.LoginViewModel
import gt.com.pixela.jetfm.ui.screens.LoginScreen

interface LoggedOut {
    sealed class OutRouting {
        object Login : OutRouting()
    }

    companion object {
        @Composable
        fun Content(
            defaultRouting: OutRouting,
            onLoggedIn: (String, String) -> Unit,
            viewModel: LoginViewModel
        ) {
            Router(defaultRouting = defaultRouting) {
                when (val current = it.last()) {
                    is OutRouting.Login -> LoginScreen(onLoggedIn = onLoggedIn, viewModel)
                }
            }
        }
    }
}