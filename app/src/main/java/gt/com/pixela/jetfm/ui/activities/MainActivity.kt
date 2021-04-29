package gt.com.pixela.jetfm.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import gt.com.pixela.jetfm.data.vm.HomeViewModel
import gt.com.pixela.jetfm.data.vm.LoginViewModel
import gt.com.pixela.jetfm.ui.screens.Home
import gt.com.pixela.jetfm.ui.screens.Login
import gt.com.pixela.jetfm.ui.theme.JetfmTheme
import gt.com.pixela.jetfm.utils.LocalHomeViewModel
import gt.com.pixela.jetfm.utils.LocalLoginViewModel
import gt.com.pixela.jetfm.utils.LocalMainNavigator

@ExperimentalAnimatedInsets
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val loginViewModel by viewModels<LoginViewModel>()
  private val homeViewModel by viewModels<HomeViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val startDestination =
      if (intent.getBooleanExtra("logged", false)) "home"
      else "login"

    setContent {
      val navigationController = rememberNavController()

      JetfmTheme {

        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
          CompositionLocalProvider(LocalMainNavigator provides navigationController) {
            NavHost(navController = navigationController, startDestination) {
              composable("login") {
                CompositionLocalProvider(LocalLoginViewModel provides loginViewModel) {
                  Login()
                }
              }
              composable("home") {
                CompositionLocalProvider(LocalHomeViewModel provides homeViewModel) {
                  homeViewModel.getHome()
                  homeViewModel.getProfile()
                  Home()
                }
              }
            }
          }
        }
      }
    }
  }
}

