package tech.pixela.jetfm.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import soup.compose.material.motion.animation.materialSharedAxisZIn
import soup.compose.material.motion.animation.materialSharedAxisZOut
import soup.compose.material.motion.navigation.MaterialMotionNavHost
import soup.compose.material.motion.navigation.composable
import soup.compose.material.motion.navigation.rememberMaterialMotionNavController
import tech.pixela.jetfm.data.model.utils.JetRoute
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.home.activity.PeriodDialog
import tech.pixela.jetfm.ui.screens.sections.Activity
import tech.pixela.jetfm.ui.screens.sections.Dashboard
import tech.pixela.jetfm.ui.screens.sections.Profile

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun Home(
    items: List<JetRoute> = listOf(JetRoute.Dashboard, JetRoute.Activity, JetRoute.Profile),
    navController: NavHostController = rememberMaterialMotionNavController(),
    mainViewModel: MainViewModel = hiltViewModel()
) {
    var currentItem: JetRoute by remember { mutableStateOf(JetRoute.Dashboard) }
    val showPeriodDialog by mainViewModel.showPeriodDialog.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.map {
                    NavigationBarItem(
                        icon = { Icon(it.icon, contentDescription = null) },
                        label = { Text(text = stringResource(id = it.screenResId)) },
                        onClick = {
                            currentItem = it
                            navController.navigate(it.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selected = it == currentItem
                    )
                }
            }
        }
    ) {
        MaterialMotionNavHost(
            navController,
            startDestination = JetRoute.Dashboard.route,
            enterTransition = { materialSharedAxisZIn(true) },
            exitTransition = { materialSharedAxisZOut(true) },
            modifier = Modifier.padding(it)
        ) {
            items.map { item ->
                composable(item.route) {
                    when (item) {
                        JetRoute.Dashboard -> {
                            Dashboard(mainViewModel)
                        }
                        JetRoute.Activity -> {
                            Activity(mainViewModel)
                        }
                        JetRoute.Profile -> {
                            Profile(mainViewModel)
                        }
                    }
                }
            }
        }

        PeriodDialog(open = showPeriodDialog, onDismiss = { mainViewModel.toggleDialog() })
    }
}
