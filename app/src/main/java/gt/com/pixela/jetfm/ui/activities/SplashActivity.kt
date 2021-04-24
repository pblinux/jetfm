package gt.com.pixela.jetfm.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import gt.com.pixela.jetfm.ui.screens.Splash
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    GlobalScope.launch { setupJetFm() }
    setContent {
      Splash()
    }
  }

  private suspend fun setupJetFm() {
    delay(3000)
    startActivity(Intent(applicationContext, MainActivity::class.java).apply {
      putExtra("logged", sharedPreferences.getString("user", null)?.let { true } ?: run { false })
    })
  }

  private val sharedPreferences by lazy {
    val key = MasterKey.Builder(applicationContext)
      .setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    EncryptedSharedPreferences.create(
      applicationContext, "jetfm_secret_preferences", key,
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
  }
}

