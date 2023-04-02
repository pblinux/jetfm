package tech.pixela.jetfm.data.model.lastfm.holders

import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.User

@Serializable
data class ProfileHolder(
    val user: User
)
