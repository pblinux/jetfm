package tech.pixela.jetfm.ui.composables.home.activity

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import tech.pixela.jetfm.data.model.utils.ActivityRoute
import tech.pixela.jetfm.ui.composables.common.JetTitle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ActivityHeader(
    pagerState: PagerState,
    tabs: List<ActivityRoute>,
    onSettingsClick: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            JetTitle(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                textAlign = TextAlign.Start
            )

            IconButton(onClick = onSettingsClick, enabled = true) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = MaterialTheme.colorScheme.background
//            indicator = { positions ->
//                TabRowDefaults.Indicator(
//                    Modifier.pagerTabIndicatorOffset(pagerState, positions),
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
        ) {
            tabs.forEach {
                Tab(
                    selected = pagerState.currentPage == tabs.indexOf(it),
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(tabs.indexOf(it)) }
                    },
                    text = {
                        Text(
                            text = stringResource(id = it.screenResId),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                )
            }
        }
    }
}
