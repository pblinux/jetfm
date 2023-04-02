package tech.pixela.jetfm.ui.screens.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import tech.pixela.jetfm.R
import tech.pixela.jetfm.ui.activities.main.MainViewModel
import tech.pixela.jetfm.ui.composables.common.result.jetPagingItems
import tech.pixela.jetfm.ui.composables.common.result.withState
import tech.pixela.jetfm.ui.composables.home.profile.Friend
import tech.pixela.jetfm.ui.composables.home.profile.InfoHeader

@ExperimentalMaterial3Api
@Composable
fun Profile(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val userInfo by mainViewModel.userInfo.collectAsState()
    val friends = mainViewModel.friends.collectAsLazyPagingItems()

    Scaffold() {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            withState(
                userInfo
            ) { data ->
                item {
                    InfoHeader(user = data.user)
                }

                item {
                    Text(
                        stringResource(R.string.your_friends),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                jetPagingItems(friends) { Friend(friend = it) }
            }
        }
    }
}
