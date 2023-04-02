package tech.pixela.jetfm.data.model.lastfm.groups

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.Friend
import tech.pixela.jetfm.data.model.lastfm.common.Meta

@Serializable
data class Friends(
    @SerialName("@attr") val meta: Meta,
    @SerialName("user") val friends: List<Friend>
)
