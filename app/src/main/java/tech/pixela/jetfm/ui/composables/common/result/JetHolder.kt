package tech.pixela.jetfm.ui.composables.common.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tech.pixela.jetfm.data.model.utils.JetResult

fun <T> LazyListScope.withState(
    state: JetResult<T>,
    content: LazyListScope.(T) -> Unit
) {
    when (state) {
        JetResult.Loading -> {
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center).size(32.dp)
                    )
                }
            }
        }
        is JetResult.Loaded -> {
            content(state.data)
        }

        is JetResult.Refresh -> {
            content(state.current)
        }

        else -> {
        }
    }
}
