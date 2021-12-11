package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.groups.Friends

@Serializable
data class FriendsResult(@SerialName("friends") val friends: Friends)