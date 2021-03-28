package gt.com.pixela.jetfm.ui.screens

import gt.com.pixela.jetfm.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.navigate
import gt.com.pixela.jetfm.data.vm.LoginState
import gt.com.pixela.jetfm.ui.composables.animations.JetCircles
import gt.com.pixela.jetfm.ui.composables.common.*
import gt.com.pixela.jetfm.ui.composables.login.LoginForm
import gt.com.pixela.jetfm.ui.theme.black
import gt.com.pixela.jetfm.utils.COLUMN_HORIZONTAL_PADDING
import gt.com.pixela.jetfm.utils.LocalLoginViewModel
import gt.com.pixela.jetfm.utils.LocalMainNavigator
import kotlinx.coroutines.launch

@Preview
@Composable
fun Login() {
  val loginViewModel = LocalLoginViewModel.current
  val loginState by loginViewModel.currentState.collectAsState()
  val mainNavigator = LocalMainNavigator.current
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()

  when (loginState) {
    LoginState.Error -> {
      scope.launch {
        scaffoldState.snackbarHostState.showSnackbar("Error", "Action", SnackbarDuration.Short)
      }
    }
    is LoginState.Loaded -> {
      loginViewModel.saveSession(
        (loginState as LoginState.Loaded).username,
        (loginState as LoginState.Loaded).key
      )
      mainNavigator.navigate("home")
    }
    else -> {
    }
  }

  Scaffold(
    backgroundColor = black,
    scaffoldState = scaffoldState,
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(horizontal = COLUMN_HORIZONTAL_PADDING),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        JetTitle()
        SmallGap()
        JetLine()
        LargeGap()
        LoginForm()
        LargeGap()
        JetButton(title = stringResource(id = R.string.enter_label),
          isLoading = loginState == LoginState.Loading,
          onClick = { loginViewModel.login() }
        )
      }
      JetCircles(
        modifier = Modifier
          .alpha(0.75f)
          .align(alignment = Alignment.BottomEnd)
          .offset(100.dp, 50.dp), size = 250
      )

      JetCircles(
        modifier = Modifier
          .alpha(0.50f)
          .align(alignment = Alignment.TopStart)
          .offset((-25).dp, (-25).dp), size = 100
      )
    }
  }
}