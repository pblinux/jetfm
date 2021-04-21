package gt.com.pixela.jetfm.ui.screens

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.ui.screens.home.Activity
import gt.com.pixela.jetfm.ui.screens.home.Dashboard
import gt.com.pixela.jetfm.ui.composables.home.JetBar
import gt.com.pixela.jetfm.ui.screens.home.Profile
import gt.com.pixela.jetfm.utils.LocalHomeViewModel

sealed class HomeScreen(val route: String, @StringRes val screenId: Int) {
  object Dashboard : HomeScreen("dashboard", R.string.dashboard)
  object Activity : HomeScreen("activity", R.string.activity)
  object Profile : HomeScreen("profile", R.string.profile)
}

val screens = listOf(HomeScreen.Dashboard, HomeScreen.Activity, HomeScreen.Profile)

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun Home() {
  val homeNavigationController = rememberNavController()
  val homeViewModel = LocalHomeViewModel.current
  val barElevated by homeViewModel.barElevated.collectAsState()

  homeNavigationController.addOnDestinationChangedListener { _, _, _ ->
    homeViewModel.updateBarElevation(0)
  }

  Scaffold(
    topBar = { JetBar(elevated = barElevated) },
    bottomBar = {
      BottomNavigation {
        val navBackStackEntry by homeNavigationController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
        screens.forEach { screen ->
          BottomNavigationItem(
            icon = {
              Icon(
                imageVector = when (screen) {
                  HomeScreen.Dashboard -> Icons.Default.Home
                  HomeScreen.Activity -> Icons.Default.List
                  HomeScreen.Profile -> Icons.Default.Person
                },
                contentDescription = stringResource(id = screen.screenId)
              )
            },
            label = { Text(stringResource(id = screen.screenId)) },
            selected = currentRoute == screen.route,
            onClick = {
              homeNavigationController.navigate(screen.route) {
                popUpTo = homeNavigationController.graph.startDestination
                launchSingleTop = true
              }
            }
          )
        }
      }
    }
  ) {
    NavHost(
      navController = homeNavigationController,
      startDestination = HomeScreen.Dashboard.route
    ) {
      composable(HomeScreen.Dashboard.route) { SwitchAnimation { Dashboard() } }
      composable(HomeScreen.Activity.route) { SwitchAnimation { Activity() } }
      composable(HomeScreen.Profile.route) { SwitchAnimation { Profile() } }
    }
  }
}

@ExperimentalAnimationApi
@Composable
fun SwitchAnimation(content: @Composable () -> Unit) {
  AnimatedVisibility(
    visible = true,
    initiallyVisible = false,
    enter = slideInVertically(
      initialOffsetY = { -40 }
    ) + expandVertically(
      expandFrom = Alignment.Top
    ) + fadeIn(
      initialAlpha = 0.3f
    ),
    exit = slideOutVertically() + shrinkVertically() + fadeOut(),
    content = content,
  )
}