package gt.com.pixela.jetfm.ui.route

import androidx.compose.runtime.Composable
import com.github.zsoltk.compose.router.Router
import gt.com.pixela.jetfm.data.vm.HomeViewModel
import gt.com.pixela.jetfm.ui.screens.HomeScreen

interface LoggedIn {
    sealed class InRouting {
        object Home : InRouting()
    }

    companion object {
        @Composable
        fun Content(
            defaultRouting: InRouting,
            viewModel: HomeViewModel
        ) {
            Router(defaultRouting = defaultRouting) {
                when (val current = it.last()) {
                    is InRouting.Home -> {
                        viewModel.getInfo()
                        viewModel.getRecentTracks()
                        viewModel.getHistoryTracks()
                        HomeScreen(viewModel)
                    }
                }
            }
        }
    }
}