package tech.pixela.jetfm.ui.composables.home.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import tech.pixela.jetfm.data.model.lastfm.base.Friend
import tech.pixela.jetfm.data.model.lastfm.base.User
import tech.pixela.jetfm.ui.composables.common.JetImage
import tech.pixela.jetfm.ui.composables.common.MediumJetSpace

@Composable
fun Friend(friend: Friend, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            JetImage(
                url = friend.image.single { it.size == "extralarge" }.url,
                modifier = Modifier.size(64.dp),
                circular = true
            )

            MediumJetSpace()

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = friend.name,
                    style = MaterialTheme.typography.titleLarge
                )
                if (friend.realName.isNotBlank()) {
                    Text(
                        text = friend.realName,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}