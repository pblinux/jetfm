package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.common.Image
import tech.pixela.jetfm.data.model.lastfm.common.RegisteredVariant

@Serializable
data class Friend(
    @SerialName("playcount") val playCount: Int,
    @SerialName("realname") val realName: String,
    val bootstrap: Int,
    val country: String,
    val image: List<Image>,
    val name: String,
    val playlists: Int,
    val registered: RegisteredVariant,
    val subscriber: Int,
    val url: String
)
