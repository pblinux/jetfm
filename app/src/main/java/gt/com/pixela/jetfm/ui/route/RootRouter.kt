package gt.com.pixela.jetfm.ui.route

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import com.github.zsoltk.compose.router.Router
import gt.com.pixela.jetfm.data.vm.LoginViewModel

interface Root {
    sealed class RootRouting {
        object LoggedOut : RootRouting()
        data class LoggedIn(val user: String, val key: String) : RootRouting()
    }

    companion object {
        @Composable
        fun Content(defaultRouting: RootRouting, viewModels: List<AndroidViewModel>) {
            Router(defaultRouting = defaultRouting) { stack ->
                val current = stack.last()
                Crossfade(current = current) {
                    when (it) {
                        is RootRouting.LoggedOut -> LoggedOut.Content(
                            defaultRouting = LoggedOut.OutRouting.Login,
                            onLoggedIn = { user, key ->
                                stack.newRoot(RootRouting.LoggedIn(user = user, key = key))
                            },
                            viewModel = viewModels
                                .firstOrNull { value -> value is LoginViewModel } as LoginViewModel
                        )
                        is RootRouting.LoggedIn -> LoggedIn.Content(
                            defaultRouting = LoggedIn.InRouting.Home, it.user, it.key
                        )
                    }
                }
            }
        }
    }
}