package gt.com.pixela.jetfm.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import gt.com.pixela.jetfm.data.source.DataStoreManager
import gt.com.pixela.jetfm.ui.screens.Splash
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagerApi
@ExperimentalAnimatedInsets
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

  @Inject
  lateinit var storeManager: DataStoreManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GlobalScope.launch { setupJetFm() }
    setContent {
      Splash()
    }
  }

  private suspend fun setupJetFm() {
    delay(2000)
    coroutineScope {
      storeManager.loggedIn.take(1).collect {
        startActivity(Intent(applicationContext, MainActivity::class.java).apply {
          flags = Intent.FLAG_ACTIVITY_NEW_TASK
          putExtra("logged", it)
        })
        finish()
      }
    }
  }

//  private val sharedPreferences by lazy {
//    val key = MasterKey.Builder(applicationContext)
//      .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
//
//    EncryptedSharedPreferences.create(
//      applicationContext, "jetfm_secret_preferences", key,
//      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//    )
//  }
}

