package gt.com.pixela.jetfm.ui.composables.home

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gt.com.pixela.jetfm.ui.composables.common.JetTitle
import gt.com.pixela.jetfm.ui.screens.HomeScreen
import gt.com.pixela.jetfm.utils.LocalHomeViewModel

@ExperimentalAnimationApi
@Composable
fun JetBar(elevated: Boolean = false, screen: HomeScreen) {
  val homeViewModel = LocalHomeViewModel.current

  val elevation: Dp by animateDpAsState(if (elevated) 12.dp else 0.dp)
  val backgroundColor: Color by animateColorAsState(
    when {
      elevated -> MaterialTheme.colors.background
      else -> {
        if (isSystemInDarkTheme()) Color.Transparent
        else Color.White
      }
    }
  )
  TopAppBar(
    actions = {
      if (screen is HomeScreen.Activity)
        AnimatedVisibility(
          visible = true,
          initiallyVisible = false,
          enter = fadeIn() + slideInHorizontally(initialOffsetX = { 40 }),
          exit = fadeOut() + shrinkOut()
        ) {
          IconButton(onClick = { homeViewModel.togglePeriodDialog() }) {
            Icon(
              imageVector = Icons.Default.Settings,
              contentDescription = "Change period", tint = when (isSystemInDarkTheme()) {
                true -> Color.White
                false -> Color.Black
              }
            )
          }
        }
    },
    title = {
      Box(
        Modifier
          .fillMaxWidth()
          .padding(top = 8.dp, bottom = 8.dp)
      ) {
        JetTitle(size = 32.sp, modifier = Modifier.align(Alignment.Center))
      }
    },
    backgroundColor = backgroundColor,
    elevation = elevation,
  )
}