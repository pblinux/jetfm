package gt.com.pixela.jetfm.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.ui.screens.home.Activity
import gt.com.pixela.jetfm.ui.screens.home.Dashboard
import gt.com.pixela.jetfm.ui.composables.home.JetBar
import gt.com.pixela.jetfm.ui.screens.home.Profile

sealed class HomeScreen(val route: String, @StringRes val screenId: Int) {
  object Dashboard : HomeScreen("dashboard", R.string.dashboard)
  object Activity : HomeScreen("activity", R.string.activity)
  object Profile : HomeScreen("profile", R.string.profile)
}

val screens = listOf(HomeScreen.Dashboard, HomeScreen.Activity, HomeScreen.Profile)

@ExperimentalFoundationApi
@Preview
@Composable
fun Home() {
  val homeNavigationController = rememberNavController()

  Scaffold(
    topBar = { JetBar() },
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
      composable(HomeScreen.Dashboard.route) { Dashboard() }
      composable(HomeScreen.Activity.route) { Activity() }
      composable(HomeScreen.Profile.route) { Profile() }
    }
  }
}