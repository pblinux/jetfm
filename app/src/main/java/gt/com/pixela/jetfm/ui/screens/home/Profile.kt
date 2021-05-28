package gt.com.pixela.jetfm.ui.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import gt.com.pixela.jetfm.data.models.ProfileInfo
import gt.com.pixela.jetfm.data.models.User
import gt.com.pixela.jetfm.data.vm.ResultState
import gt.com.pixela.jetfm.ui.composables.common.ErrorView
import gt.com.pixela.jetfm.ui.composables.common.LoadingItem
import gt.com.pixela.jetfm.ui.composables.common.LoadingView
import gt.com.pixela.jetfm.ui.composables.common.UninitializedView
import gt.com.pixela.jetfm.ui.composables.home.FriendItem
import gt.com.pixela.jetfm.ui.composables.profile.InfoHeader
import gt.com.pixela.jetfm.utils.LocalHomeViewModel

@Composable
fun Profile() {
  val homeViewModel = LocalHomeViewModel.current
  val profileState by homeViewModel.profile.collectAsState()
  val friends: LazyPagingItems<User> = homeViewModel.friends.collectAsLazyPagingItems()
  val scrollState = rememberLazyListState()
  homeViewModel.updateBarElevation(scrollState.firstVisibleItemIndex)

  when (profileState) {
    ResultState.Uninitialized -> UninitializedView()
    ResultState.Loading -> LoadingView()
    is ResultState.Error -> ErrorView {}
    is ResultState.Loaded<*> -> {
      val loadedData = (profileState as ResultState.Loaded<*>).data as ProfileInfo

      LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp)
      ) {
        item {
          InfoHeader(user = loadedData.user)
        }

        item {
          Spacer(Modifier.height(24.dp))
        }

        item {
          Text("Your friends:")
        }

        items(friends, itemContent = {
          FriendItem(user = it!!)
        })

        friends.apply {
          when {
            loadState.refresh is LoadState.Loading -> {
              item { LoadingView(Modifier.fillParentMaxSize()) }
            }
            loadState.append is LoadState.Loading -> {
              item { LoadingItem(Modifier.fillParentMaxWidth()) }
            }
          }
        }

        item { Spacer(Modifier.height(40.dp)) }
      }
    }
    else -> {}
  }
}
