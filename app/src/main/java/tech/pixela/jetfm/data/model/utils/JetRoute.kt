package tech.pixela.jetfm.data.model.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import tech.pixela.jetfm.R

sealed class JetRoute(val route: String, @StringRes val screenResId: Int, val icon: ImageVector) {
    object Dashboard : JetRoute("dashboard", R.string.dashboard, Icons.Default.Home)
    object Activity : JetRoute("activity", R.string.activity, Icons.Default.List)
    object Profile : JetRoute("profile", R.string.profile, Icons.Default.Person)
}
