package tech.pixela.jetfm.ui.composables.common.result

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import tech.pixela.jetfm.ui.composables.common.CustomJetSpace

@ExperimentalFoundationApi
@Composable
fun <T : Any> JetPagingColumn(
    items: LazyPagingItems<T>,
    scrollState: LazyListState = rememberLazyListState(),
    itemContent: @Composable (T) -> Unit
) {
    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        jetPagingItems(items, itemContent)
    }
}

fun <T : Any> LazyListScope.jetPagingItems(
    items: LazyPagingItems<T>,
    itemContent: @Composable (T) -> Unit
) {
    items(items, itemContent = { itemContent(it!!) })
    items.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item { CircularProgressIndicator(Modifier.size(48.dp)) }
            }
            loadState.append is LoadState.Loading -> {
                item { CircularProgressIndicator(Modifier.size(48.dp)) }
            }
        }
    }
    item {
        CustomJetSpace(size = 64.0)
    }
}
