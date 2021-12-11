package tech.pixela.jetfm.ui.activities.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import tech.pixela.jetfm.data.model.utils.JetResult
import tech.pixela.jetfm.ui.activities.main.MainActivity
import tech.pixela.jetfm.ui.screens.Login
import tech.pixela.jetfm.ui.theme.JetfmTheme

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@ExperimentalCoilApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetfmTheme {
                val loginState by loginViewModel.loginState.collectAsState()
                LaunchedEffect(loginState) {
                    when (loginState) {
                        is JetResult.Loaded -> {
                            navigateToHome()
                        }
                        else -> {
                        }
                    }
                }
                Login()
            }
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }
}