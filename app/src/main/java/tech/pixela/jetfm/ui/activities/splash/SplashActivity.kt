package tech.pixela.jetfm.ui.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import tech.pixela.jetfm.data.source.local.datastore.DataStoreManager
import tech.pixela.jetfm.ui.activities.main.MainActivity
import tech.pixela.jetfm.ui.activities.login.LoginActivity
import tech.pixela.jetfm.ui.screens.Splash
import tech.pixela.jetfm.ui.theme.JetfmTheme
import javax.inject.Inject

@DelicateCoroutinesApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterial3Api
@ExperimentalPagerApi
@SuppressLint("CustomSplashScreen")
@ExperimentalFoundationApi
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    @Inject
    lateinit var storeManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch { setupJetFm() }

        setContent {
            JetfmTheme {
                Splash()
            }
        }
    }

    private suspend fun setupJetFm() {
        delay(2000)
        coroutineScope {
            storeManager.loggedIn.take(1).collect {
                when (it) {
                    true -> {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                    }
                    false -> {
                        startActivity(Intent(applicationContext, LoginActivity::class.java))
                    }
                }
                finish()
            }
        }
    }
}