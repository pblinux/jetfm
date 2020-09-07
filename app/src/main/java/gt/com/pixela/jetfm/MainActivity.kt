package gt.com.pixela.jetfm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import androidx.preference.PreferenceManager
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.savedinstancestate.BundleScope
import gt.com.pixela.jetfm.data.vm.LoginViewModel
import gt.com.pixela.jetfm.ui.route.Root
import gt.com.pixela.jetfm.ui.theme.JetfmTheme

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Preferences
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val default = preferences.getString("user", null)?.let {
            Root.RootRouting.LoggedIn(user = it, key = preferences.getString("key", "") ?: "")
        } ?: run { Root.RootRouting.LoggedOut }

        setContent {
            JetfmTheme {
                Providers(values = arrayOf(AmbientBackPressHandler provides backPressHandler)) {
                    BundleScope(savedInstanceState) {
                        Root.Content(
                            defaultRouting = default,
                            listOf(loginViewModel)
                        )
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }

    }
}
