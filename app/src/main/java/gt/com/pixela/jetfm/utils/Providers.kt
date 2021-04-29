package gt.com.pixela.jetfm.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import gt.com.pixela.jetfm.data.vm.HomeViewModel
import gt.com.pixela.jetfm.data.vm.LoginViewModel

val LocalMainNavigator = compositionLocalOf<NavHostController> { error("No navigation found") }
val LocalLoginViewModel = compositionLocalOf<LoginViewModel> { error("No view model found") }
val LocalHomeViewModel = compositionLocalOf<HomeViewModel> { error("No view model found") }