package tech.pixela.jetfm.ui.composables.home.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tech.pixela.jetfm.R
import tech.pixela.jetfm.data.model.lastfm.base.User
import tech.pixela.jetfm.ui.composables.common.*

@Composable
fun InfoHeader(user: User) {
    Column {
        JetTitle(textAlign = TextAlign.Start)
        MediumJetSpace()
        JetHeadline(text = stringResource(R.string.your_profile))
        LargeJetSpace()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            JetImage(
                url = user.image.single { it.size == "extralarge" }.url,
                modifier = Modifier.size(120.dp),
                circular = true
            )
            MediumJetSpace()
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = user.realname,
                    style = MaterialTheme.typography.headlineMedium
                )
                SmallJetSpace()
                Text(
                    text = user.name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        LargeJetSpace()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoHeaderItem(user.playcount, stringResource(R.string.scrobbles))
            InfoHeaderItem(user.subscriber, stringResource(R.string.subscribers))
            InfoHeaderItem(user.playlists, stringResource(R.string.playlists))
        }
    }
}

@Composable
fun InfoHeaderItem(number: Int, description: String) {
    Column {
        Text(
            "$number",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium
        )
        XSmallJetSpace()
        Text(
            description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}